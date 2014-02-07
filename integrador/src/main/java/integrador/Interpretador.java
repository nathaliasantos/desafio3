package integrador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import soa32.resources.cliente.Cliente;
import soa32.resources.cliente.ClienteResource;
import soa32.resources.cliente.ClienteResourcePortType;
import soa32.resources.notaFiscal.NotaFiscal;
import soa32.resources.notaFiscal.NotaFiscalResource;
import soa32.resources.notaFiscal.NotaFiscalResourcePortType;
import soa32.resources.notaFiscal.Status;
import soa32.resources.produto.Produto;
import soa32.resources.produto.ProdutoResource;
import soa32.resources.produto.ProdutoResourcePortType;
import Utils.ListaUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Interpretador {

	ArrayList<Cliente> clientes = new ArrayList<Cliente>();

	public static void main(String[] args) {
		new Interpretador();
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

	private void adicionaNovosClientes(ArrayList<Cliente> clientesNovos) {
		ClienteResource c = new ClienteResource();
		ClienteResourcePortType cl = c.getClienteResourcePort();
		for (Cliente cliente : clientesNovos) {
			System.out.println("CRIAR: " + cliente.getDataNascimento());
			cl.create(cliente);
		}
	}

	private static ClienteResourcePortType criaClienteResourcePortType() {
		ClienteResource c = new ClienteResource();
		return c.getClienteResourcePort();
	}

	private ProdutoResourcePortType criaProdutoResourcePortType() {
		ProdutoResource p = new ProdutoResource();
		return p.getProdutoResourcePort();
	}

	private NotaFiscalResourcePortType criaNotaFiscalResourcePortType() {
		NotaFiscalResource n = new NotaFiscalResource();
		return n.getNotaFiscalResourcePort();
	}

	private String printaCliente(Cliente c) {
		return c.getId() + "/" + c.getCelular() + "/" + c.getCpf() + "/"
				+ c.getEmail() + "/" + c.getNome() + "/"
				+ c.getDataNascimento();
	}

	private class ThreadClient implements Runnable {
		@Override
		public void run() {
			ArrayList<Object> clientesCaptacaoObject = getListFromUrl(
					"/captacao/api/clientes.json", CLIENTE);
			ArrayList<Cliente> clientesCaptacao = new ArrayList<Cliente>();
			for (int i = 0; i < clientesCaptacaoObject.size(); i++)
				clientesCaptacao.add((Cliente) clientesCaptacaoObject.get(i));

			ArrayList<Cliente> clientesFaturamento = (ArrayList) criaClienteResourcePortType()
					.list();

			System.out.println("CLIENTES FATURAMENTO ANTES: ");
			for (Cliente a : clientesFaturamento) {
				System.out.println(printaCliente(a));
			}

			System.out.println("CLIENTES CAPTACAO ANTES: ");
			for (Cliente a : clientesCaptacao) {
				System.out.println(printaCliente(a));
			}

			System.out.println("CLIENTE PARA ADD: ");
			ArrayList<Cliente> clientesNovos;
			clientesNovos = ListaUtils.listaAdicionarCliente(
					clientesFaturamento, clientesCaptacao);

			for (Cliente a : clientesNovos) {
				System.out.println(printaCliente(a));
			}

			adicionaNovosClientes(clientesNovos);

			System.out.println("CLIENTES FATURAMENTO NOVOS: ");
			for (Cliente a : clientesFaturamento) {
				System.out.println(printaCliente(a));
			}
			System.out.println("CLIENTES CAPTACAO NOVOS: ");
			for (Cliente a : clientesCaptacao) {
				System.out.println(printaCliente(a));
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

			ArrayList<Produto> produtosFaturamento = (ArrayList) criaProdutoResourcePortType().list();
			
			ArrayList<Produto> produtosNovos;
			produtosNovos = ListaUtils.listaAdicionarProduto(produtosCaptacao,
					produtosFaturamento);
			adicionarNovosProdutos(produtosNovos);
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

			ArrayList<NotaFiscal> notaFiscalFaturamento = (ArrayList) criaNotaFiscalResourcePortType()
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
							criaNotaFiscalResourcePortType().delete(
									notaFiscal.getId());
							notaFiscal.setStatus(Status.EMITIDA);
							criaNotaFiscalResourcePortType().create(notaFiscal);

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

					criaNotaFiscalResourcePortType().create(novaNotaFiscal);
				}
			}
		}
	}

	private ArrayList<Object> transformIntoCliente(JsonArray lista) {
		ArrayList<Object> listaClientes = new ArrayList<Object>();
		for (int i = 0; i < lista.size(); i++) {
			JsonObject objetoAtual = lista.get(i).getAsJsonObject();
			Cliente novoCliente = new Cliente();
			novoCliente.setId(Long.parseLong(objetoAtual.get("id")
					.getAsString()));
			novoCliente.setCelular(objetoAtual.get("celular").getAsString());
			novoCliente.setCpf(objetoAtual.get("cpf").getAsString());
			novoCliente.setEmail(objetoAtual.get("email").getAsString());
			novoCliente.setNome(objetoAtual.get("nome").getAsString());

			String[] split = objetoAtual.get("dataNascimento").getAsString()
					.split("T")[0].split("-");

			XMLGregorianCalendar date = null;
			try {
				date = DatatypeFactory.newInstance().newXMLGregorianCalendar(
						new GregorianCalendar(Integer.parseInt(split[0]),
								Integer.parseInt(split[1]), Integer
										.parseInt(split[2])));
			} catch (DatatypeConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			novoCliente.setDataNascimento(date);
			listaClientes.add(novoCliente);
		}

		return listaClientes;
	}

	private ArrayList<Object> transformIntoProduto(JsonArray lista) {
		ArrayList<Object> listaProdutos = new ArrayList<Object>();
		for (int i = 0; i < lista.size(); i++) {
			JsonObject objetoAtual = lista.get(i).getAsJsonObject();
			Produto novoProduto = new Produto();
			novoProduto.setId(Long.parseLong(objetoAtual.get("id")
					.getAsString()));
			novoProduto.setNome(objetoAtual.get("nome").getAsString());
			novoProduto.setDepartamento(objetoAtual.get("departamento")
					.getAsString());
			novoProduto.setFabricante(objetoAtual.get("fabricante")
					.getAsString());
			novoProduto.setTamanho(objetoAtual.get("tamanho").getAsString());
			novoProduto.setUrlImage(objetoAtual.get("urlImage").getAsString());
			novoProduto.setItemExclusivo(new Boolean(objetoAtual.get(
					"itemExclusivo").getAsString()));
			novoProduto.setDataValidade(objetoAtual.get("dataValidade")
					.getAsString());
			novoProduto.setPrecoDeCusto(Long.parseLong(objetoAtual.get(
					"dataValidade").getAsString()));

			listaProdutos.add(novoProduto);
		}

		return listaProdutos;
	}

	private ArrayList<Object> transformIntoPedido(JsonArray lista) {
		ArrayList<Object> listaPedidos = new ArrayList<Object>();
		for (int i = 0; i < lista.size(); i++) {
			JsonObject objetoAtual = lista.get(i).getAsJsonObject();
			Pedido novoPedido = new Pedido();
			novoPedido.setId(Long
					.parseLong(objetoAtual.get("id").getAsString()));
			novoPedido.setLote(Long.parseLong(objetoAtual.get("lote")
					.getAsString()));
			novoPedido.setNotaFiscal(Long.parseLong(objetoAtual.get(
					"notaFiscal").getAsString()));
			novoPedido.setStatus(objetoAtual.get("fabricante").getAsString());
			novoPedido.setItens(null); // TODO fazer
			novoPedido.setNotaFiscalLink(objetoAtual.get("notaFiscalLink")
					.getAsString());
			novoPedido.setLoteLink(new Boolean(objetoAtual.get("loteLink")
					.getAsString()));

			listaPedidos.add(novoPedido);
		}

		return listaPedidos;
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
				return transformIntoCliente(lista);
			if (tipo == PRODUTO)
				return transformIntoProduto(lista);
			if (tipo == PEDIDO)
				return transformIntoPedido(lista);

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
}
