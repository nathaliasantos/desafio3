package integrador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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

	public Interpretador() {
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(3);
	    executor.scheduleAtFixedRate(new ThreadClient(), 0, 5000, TimeUnit.MILLISECONDS);
	    executor.scheduleAtFixedRate(new ThreadProduto(), 100, 5000, TimeUnit.MILLISECONDS);
	    executor.scheduleAtFixedRate(new ThreadPedido(), 200, 5000, TimeUnit.MILLISECONDS);
	    executor.scheduleAtFixedRate(new ThreadNotaFiscal(), 300, 5000, TimeUnit.MILLISECONDS);
	}

	// VER SE FUNFA
	private void adicionaNovosClientes(ArrayList<Cliente> clientesNovos) {
		ClienteResource c = new ClienteResource();
		ClienteResourcePortType cl = c.getClienteResourcePort();
		for (Cliente cliente : clientesNovos)
			cl.create(cliente);
	}

	// VER SE FUNFA
	private void deletaClientes(ArrayList<Cliente> clientesDeletados) {
		ClienteResource c = new ClienteResource();
		ClienteResourcePortType cl = c.getClienteResourcePort();
		for (Cliente cliente : clientesDeletados)
			cl.delete(cliente.getId());
	}
	private  ClienteResourcePortType criaClienteResourcePortType(){
		ClienteResource c = new ClienteResource();
		return c.getClienteResourcePort();		
	}
	
	private  ProdutoResourcePortType criaProdutoResourcePortType(){
		ProdutoResource p = new ProdutoResource();
		return p.getProdutoResourcePort();
	}
	
	private  NotaFiscalResourcePortType criaNotaFiscalResourcePortType(){
		NotaFiscalResource n = new NotaFiscalResource();
		return n.getNotaFiscalResourcePort();
	}
	
	private class ThreadClient implements Runnable{
		@Override
		public void run() {
			ArrayList<Object> clientesCaptacaoObject = getListFromUrl("/captacao/api/clientes.json",CLIENTE);
			ArrayList<Cliente> clientesCaptacao = new ArrayList<Cliente>();
			for (int i=0;i<clientesCaptacaoObject.size();i++)
				clientesCaptacao.add((Cliente)clientesCaptacaoObject.get(i));
			
			ArrayList<Cliente> clientesFaturamento = (ArrayList)criaClienteResourcePortType().list();
			ArrayList<Cliente> clientesNovos;
			clientesNovos = ListaUtils.listaAdicionar(clientesFaturamento, clientesCaptacao);
			
			ArrayList<Cliente> clientesExcluidos;
			clientesExcluidos = ListaUtils.listaDeletar(clientesFaturamento, clientesCaptacao);
			
			adicionaNovosClientes(clientesNovos);
			deletaClientes(clientesExcluidos);
		}		
	}
	
	private class ThreadProduto implements Runnable{
		@Override
		public void run() {
			ArrayList<Object> produtosCaptacaoObject = getListFromUrl("/captacao/api/produtos.json",PRODUTO);
			ArrayList<Produto> produtosCaptacao = new ArrayList<Produto>();
			for (int i=0;i<produtosCaptacaoObject.size();i++)
				produtosCaptacao.add((Produto)produtosCaptacaoObject.get(i));
			
			ArrayList<Produto> produtosFaturamento = (ArrayList)criaProdutoResourcePortType().list();
			ArrayList<Produto> produtosNovos;
			produtosNovos = ListaUtils.listaAdicionar(produtosCaptacao, produtosFaturamento);
			
			ArrayList<Produto> ProdutosExcluidos;
			ProdutosExcluidos = ListaUtils.listaDeletar(produtosCaptacao, produtosFaturamento);
			
//          ESPERAR A NAT DAR COMMIT NO adionaNovosProdutos(ArrayList<>) PARA DAR PULL			
//			adicionaNovosProdutos(produtosNovos);
//			deletaProdutos(produtosExcluidos);
		}		
	}
	
	private class ThreadPedido implements Runnable{
		@Override
		public void run() {	
			ArrayList<Object> pedidoCaptacaoObject = getListFromUrl("/captacao/api/pedido.json",PEDIDO);
			ArrayList<Pedido> pedidoCaptacao = new ArrayList<Pedido>();
			for (int i=0;i<pedidoCaptacaoObject.size();i++)
				pedidoCaptacao.add((Pedido)pedidoCaptacaoObject.get(i));
			
			ArrayList<NotaFiscal> notaFiscalFaturamento = (ArrayList)criaNotaFiscalResourcePortType().list();

			for (Pedido pedido : pedidoCaptacao) {
				if (pedido.notaFiscal != null)
					pedidoCaptacao.remove(pedido);
			}
			
			for (NotaFiscal notaFiscal : notaFiscalFaturamento) {
				for (Pedido pedido : pedidoCaptacao){			
				
					 if ((notaFiscal.getPedido() == pedido.id) && (notaFiscal.getStatus() == Status.PROCESSADA))
						 
						 //enviar para captacao
				}
			}
			
			pedidosSemNotaFiscal = ListaUtils.listaAdicionar(pedidoCaptacao, pedidosFaturamento);
			
			ArrayList<NotaFiscal> PedidosExcluidos;
			PedidosExcluidos = ListaUtils.listaDeletar(pedidoCaptacao, pedidosFaturamento);
					
	}
	private class ThreadNotaFiscal implements Runnable{
		@Override
		public void run() {			
		}		
	}
	
	private ArrayList<Object> transformIntoCliente(JsonArray lista) {
		ArrayList<Object> listaClientes = new ArrayList<Object>();
		for (int i = 0; i < lista.size(); i++) {
			JsonObject objetoAtual = lista.get(i).getAsJsonObject();
			Cliente novoCliente = new Cliente();
			novoCliente.setId(new Long(Integer.parseInt(objetoAtual.get("id")
					.getAsString())));
			novoCliente.setCelular(objetoAtual.get("celular").getAsString());
			novoCliente.setCpf(objetoAtual.get("cpf").getAsString());
			novoCliente.setEmail(objetoAtual.get("email").getAsString());
			novoCliente.setNome(objetoAtual.get("nome").getAsString());

			Date data = new Date(objetoAtual.get("dataNascimento")
					.getAsString());
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(data);
			XMLGregorianCalendar date2 = null;
			try {
				date2 = DatatypeFactory.newInstance()
						.newXMLGregorianCalendar(c);
			} catch (DatatypeConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			novoCliente.setDataNascimento(date2);
			listaClientes.add(novoCliente);
		}

		return listaClientes;
	}

	private ArrayList<Object> transformIntoProduto(JsonArray lista) {
		ArrayList<Object> listaProdutos = new ArrayList<Object>();
		for (int i = 0; i < lista.size(); i++) {
			JsonObject objetoAtual = lista.get(i).getAsJsonObject();

		}

		return listaProdutos;
	}

	private ArrayList<Object> transformIntoPedido(JsonArray lista) {
		ArrayList<Object> listaPedidos = new ArrayList<Object>();
		for (int i = 0; i < lista.size(); i++) {
			JsonObject objetoAtual = lista.get(i).getAsJsonObject();
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
			JsonObject retornoJson = (JsonObject) (new JsonParser())
					.parse(total);

			JsonArray lista = retornoJson.getAsJsonArray();

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

}
