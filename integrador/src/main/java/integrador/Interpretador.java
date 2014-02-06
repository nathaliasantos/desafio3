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
import soa32.resources.produto.Produto;

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

	private class ThreadClient implements Runnable{
		@Override
		public void run() {
		}		
	}
	private class ThreadProduto implements Runnable{
		@Override
		public void run() {			
		}		
	}
	private class ThreadPedido implements Runnable{
		@Override
		public void run() {			
		}		
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

			Date data = new Date(objetoAtual.get("dataNascimento").getAsString());
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

	public static void adicionarNovosProdutos(List<Produto> novosProdutos){
		 try {
				URL url = new URL("http://dls98:8181/captacao/api/produtos.json");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
		 
				OutputStream os = conn.getOutputStream();
				
				JsonObject json = new JsonObject();
				
				for (Produto produto : novosProdutos)
				{
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
		 
				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));
		 
				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}
				conn.disconnect();
				
			}catch (MalformedURLException e) {
			
				e.printStackTrace();
			
			} catch (IOException e) {
			
				e.printStackTrace();
			}
	}
	
	public static void excluirProdutos(String id){
		 try {
				URL url = new URL("http://dls98:8181/captacao/api/produtos?id="+ id);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("DELETE");
				conn.disconnect();
				
			}catch (MalformedURLException e) {
			
				e.printStackTrace();
			
			} catch (IOException e) {
			
				e.printStackTrace();
			}
	}
	
}
