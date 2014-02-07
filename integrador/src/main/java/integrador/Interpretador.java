package integrador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import soa32.resources.cliente.Cliente;
import soa32.resources.notaFiscal.NotaFiscal;
import soa32.resources.notaFiscal.Status;
import soa32.resources.produto.Produto;
import Utils.ClienteUtils;
import Utils.ListaUtils;
import Utils.NotaFiscalUtils;
import Utils.PedidoUtils;
import Utils.ProdutoUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Interpretador {

	public static void main(String[] args) {
		new Interpretador();
//		Cliente c1 =  criaClienteResourcePortType().get((long)1);
//		c1.setId((long)21);
//		c1.setNome("NOVO");
//		adicionarNovosClientesParaTeste(c1);
		//Cliente c1 =  criaClienteResourcePortType().get((long)1);
		//c1.setId((long)21);
		//c1.setNome("NOVO");
		//adicionarNovosClientesParaTeste(c1);
	}

	public Interpretador() {
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(
				3);
		executor.scheduleAtFixedRate(new ThreadClient(), 0, 5000,
				TimeUnit.MILLISECONDS);
		executor.scheduleAtFixedRate(new ThreadProduto(), 100, 5000,
				TimeUnit.MILLISECONDS);
		executor.scheduleAtFixedRate(new ThreadPedido(), 200, 5000,
				TimeUnit.MILLISECONDS);

	}

	private class ThreadClient implements Runnable {
		@Override
		public void run() {
			ArrayList<Object> clientesCaptacaoObject = getListFromUrl(
					"/captacao/api/clientes.json", CLIENTE);
			ArrayList<Cliente> clientesCaptacao = new ArrayList<Cliente>();
			for (int i = 0; i < clientesCaptacaoObject.size(); i++)
				clientesCaptacao.add((Cliente) clientesCaptacaoObject.get(i));

			ArrayList<Cliente> clientesFaturamento = (ArrayList) ClienteUtils.criaClienteResourcePortType()
					.list();

			System.out.println("CLIENTES FATURAMENTO ANTES: ");
			for (Cliente a : clientesFaturamento) {
				System.out.println(ClienteUtils.printaCliente(a));
			}

			System.out.println("CLIENTES CAPTACAO ANTES: ");
			for (Cliente a : clientesCaptacao) {
				System.out.println(ClienteUtils.printaCliente(a));
			}

			
			ArrayList<Cliente> clientesNovos;
			clientesNovos = ListaUtils.listaAdicionarCliente(
					clientesFaturamento, clientesCaptacao);

			System.out.println("CLIENTE PARA ADD: ");
			for (Cliente a : clientesNovos) {
				System.out.println(ClienteUtils.printaCliente(a));
			}

			ClienteUtils.adicionarNovosClientes(clientesNovos);

			System.out.println("CLIENTES FATURAMENTO DEPOIS: ");
			for (Cliente a : clientesFaturamento) {
				System.out.println(ClienteUtils.printaCliente(a));
			}
			System.out.println("CLIENTES CAPTACAO DEPOIS: ");
			for (Cliente a : clientesCaptacao) {
				System.out.println(ClienteUtils.printaCliente(a));
			}
		}
	}

	private class ThreadProduto implements Runnable {
		@Override
		public void run() {
			ArrayList<Object> produtosCaptacaoObject = getListFromUrl(
					"/captacao/api/produtos.json", PRODUTO);
			
			ArrayList<Produto> produtosCaptacao = new ArrayList<Produto>();
			
			for (int i = 0; i < produtosCaptacaoObject.size(); i++)
				produtosCaptacao.add((Produto) produtosCaptacaoObject.get(i));

			ArrayList<Produto> produtosFaturamento = (ArrayList) ProdutoUtils.criaProdutoResourcePortType().list();
			
			System.out.println("PRODUTO FATURAMENTO ANTES: ");
			for (Produto a : produtosFaturamento) {
				System.out.println(ProdutoUtils.printaProduto(a));
			}

			System.out.println("PRODUTO CAPTACAO ANTES: ");
			for (Produto a : produtosCaptacao) {
				System.out.println(ProdutoUtils.printaProduto(a));
			}
			
			ArrayList<Produto> produtosNovos;
			produtosNovos = ListaUtils.listaAdicionarProduto(produtosCaptacao,
					produtosFaturamento);
			
			System.out.println("PRODUTO ADD: ");
			for (Produto a : produtosNovos) {
				System.out.println(ProdutoUtils.printaProduto(a));
			}
			
			ProdutoUtils.adicionarNovosProdutos(produtosNovos);
			
			System.out.println("PRODUTO FATURAMENTO DEPOIS: ");
			for (Produto a : produtosFaturamento) {
				System.out.println(ProdutoUtils.printaProduto(a));
			}

			System.out.println("PRODUTO CAPTACAO DEPOIS: ");
			for (Produto a : produtosCaptacao) {
				System.out.println(ProdutoUtils.printaProduto(a));
			}
		}		
	}

	private class ThreadPedido implements Runnable {
		@Override
		public void run() {
			ArrayList<Object> pedidoCaptacaoObject = getListFromUrl(
					"/captacao/api/pedido.json", PEDIDO);
			ArrayList<Pedido> pedidoCaptacao = new ArrayList<Pedido>();
			for (int i = 0; i < pedidoCaptacaoObject.size(); i++)
				pedidoCaptacao.add((Pedido) pedidoCaptacaoObject.get(i));

			ArrayList<NotaFiscal> notaFiscalFaturamento = (ArrayList) NotaFiscalUtils.criaNotaFiscalResourcePortType()
					.list();

			for (Pedido pedido : pedidoCaptacao) {
				if (pedido.getNotaFiscal() != null)
					pedidoCaptacao.remove(pedido);
			}

			for (Pedido pedido : pedidoCaptacao) {
				Long ultimoIdNotaFiscal = new Long(0);
				boolean existeNotaFiscalParaOProduto = false;

				for (NotaFiscal notaFiscal : notaFiscalFaturamento) {
					if (notaFiscal.getPedido() == pedido.getId()) {
						existeNotaFiscalParaOProduto = true;
						if (notaFiscal.getStatus() == Status.PROCESSADA) {
							NotaFiscalUtils.criaNotaFiscalResourcePortType().delete(
									notaFiscal.getId());
							notaFiscal.setStatus(Status.EMITIDA);
							NotaFiscalUtils.criaNotaFiscalResourcePortType().create(notaFiscal);

							Long idNotaFiscal = notaFiscal.getId();
							pedido.setNotaFiscal(idNotaFiscal);
							// removerPedidoDesatualizado(Long idDoPedido)
							// adicionarNovoPedido(Pedido pedido)

							// perguntar o que é nota fiscalLink
						}
					}
					if (notaFiscal.getId() > ultimoIdNotaFiscal)
						ultimoIdNotaFiscal = new Long(notaFiscal.getId());
				}
				if (!existeNotaFiscalParaOProduto) {
					NotaFiscal novaNotaFiscal = new NotaFiscal();
					novaNotaFiscal.setId(ultimoIdNotaFiscal);
					novaNotaFiscal.setNumero(new Long(0));
					novaNotaFiscal.setPedido(pedido.getId());
					novaNotaFiscal.setStatus(null);

					NotaFiscalUtils.criaNotaFiscalResourcePortType().create(novaNotaFiscal);
				}
			}
		}
	}

	private final int CLIENTE = 1, PRODUTO = 2, PEDIDO = 3;

	private ArrayList<Object> getListFromUrl(String strUrl, int tipo) {
		try {
			URL url = new URL("http://dls98:8181" + strUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			String total = "";
			while ((output = br.readLine()) != null) {
				total += output;
			}

			JsonArray lista = (JsonArray) (new JsonParser()).parse(total);

			conn.disconnect();
			if (tipo == CLIENTE)
				return ClienteUtils.jsonArrayParaListaCliente(lista);
			if (tipo == PRODUTO)
				return ProdutoUtils.jsonArrayParaListaProduto(lista);
			if (tipo == PEDIDO)
				return PedidoUtils.jsonArrayParaListaPedido(lista);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static void adicionarNovosProdutos(ArrayList<Produto> novosProdutos) {

		try {
			URL url = new URL("http://dls98:8181/captacao/api/produtos.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			OutputStream os = conn.getOutputStream();

			JsonObject json = new JsonObject();

			for (Produto produto : novosProdutos) {
				
				json.addProperty("id", produto.getId());
				json.addProperty("nome", produto.getNome());
				json.addProperty("departamento", produto.getDepartamento());
				json.addProperty("fabricante", produto.getFabricante());
				json.addProperty("precoDeCusto", produto.getPrecoDeCusto());
				json.addProperty("dataValidade", produto.getDataValidade());
				json.addProperty("tamanho", produto.getTamanho());
				json.addProperty("urlImage", produto.getUrlImage());
				json.addProperty("itemExclusivo", produto.isItemExclusivo());

				os.write(json.toString().getBytes());
				os.flush();
			}

			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			conn.disconnect();
		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	public static void adicionarNovosProdutosT() {

	}
	public static void excluirProdutos(String id) {

		try {
			URL url = new URL("http://dls98:8181/captacao/api/produtos.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			OutputStream os = conn.getOutputStream();

			String input = "{\"id\" : 42, \"nome\" : \"TESTE ID IDENTITY\", \"departamento\" : \"MONITORES\",  \"fabricante\" : \"BENQ\", \"precoDeCusto\" : 100, \"dataValidade\" : null, \"tamanho\" : null, \"urlImage\" : null, \"itemExclusivo\" : null}";
			 
			os.write(input.getBytes());
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			conn.disconnect();
		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private boolean igual(Cliente a, Cliente b) {
		return a.getId() == b.getId();
	}

	public ArrayList<Cliente> diferenca(ArrayList<Cliente> list1,
			ArrayList<Cliente> list2) {
		ArrayList<Cliente> list = new ArrayList<Cliente>();
		for (Cliente a : list1) {
			boolean existe = false;
			for (Cliente b : list2) {
				if (igual(a, b))
					existe = true;
			}
			if (!existe)
				list.add(a);
		}
		return list;
	}

	public ArrayList<Cliente> listaAdicionar(ArrayList<Cliente> antiga,
			ArrayList<Cliente> nova) {
		return diferenca(nova, antiga);
	}

	public ArrayList<Cliente> listaDeletar(ArrayList<Cliente> antiga,
			ArrayList<Cliente> nova) {
		return diferenca(antiga, nova);
	}
}
