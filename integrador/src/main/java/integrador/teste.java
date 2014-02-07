package integrador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.google.gson.JsonObject;

import soa32.resources.cliente.Cliente;
import soa32.resources.cliente.ClienteResource;
import soa32.resources.cliente.ClienteResourcePortType;
import soa32.resources.notaFiscal.NotaFiscal;
import soa32.resources.notaFiscal.NotaFiscalResource;
import soa32.resources.notaFiscal.NotaFiscalResourcePortType;
import soa32.resources.produto.Produto;
import soa32.resources.produto.ProdutoResource;
import soa32.resources.produto.ProdutoResourcePortType;

public class teste {
	public static void main(String[] args) { // FATURAMENTO
		listarNotasFiscais();
		System.out.println("////////////////////////////////////////////");
		listarClientes();
		System.out.println("////////////////////////////////////////////");
		listarProdutos();
	}

	private static void listarProdutos() {
		ProdutoResource p = new ProdutoResource();
		ProdutoResourcePortType pr = p.getProdutoResourcePort();
		ArrayList<Produto> listaProduto = (ArrayList<Produto>) pr.list();
		System.out.println("listagem do numero dos produtos:");
		for (Produto produto : listaProduto) {
			System.out.println(produto.getNome());
		}
	}

	private static void listarClientes() {
		ClienteResource c = new ClienteResource();
		ClienteResourcePortType cl = c.getClienteResourcePort();
		List<Cliente> listaCliente = cl.list();
		System.out.println("listagem do nome dos clientes:");
		for (Cliente cliente : listaCliente) {
			System.out.println(cliente.getId());
		}
	}

	private static void listarNotasFiscais() {
		NotaFiscalResource n = new NotaFiscalResource();
		NotaFiscalResourcePortType no = n.getNotaFiscalResourcePort();
		List<NotaFiscal> listaNota = no.list();
		System.out.println("listagem do numero das notas fiscais:");
		for (NotaFiscal notaFiscal : listaNota) {
			System.out.println(notaFiscal.getId() + "//"
					+ notaFiscal.getNumero() + "//" + notaFiscal.getPedido()
					+ "//" + notaFiscal.getStatus());
		}
	}

	public static void criarClientePadraoParaTeste() {
		XMLGregorianCalendar today = null;
		try {
			today = DatatypeFactory.newInstance().newXMLGregorianCalendar(
					new GregorianCalendar(2008, 10, 1));
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Cliente c1 = new Cliente();
		c1.setCelular("999999999");
		c1.setId((long) 28);
		c1.setNome("TESTEID28");
		c1.setCpf("111.111.111-11");
		c1.setEmail("joaninha@teste.com");
		c1.setDataNascimento(today);
		adicionarNovosClientesNaCaptacaoParaTeste(c1);
	}

	public static void adicionarNovosClientesNaCaptacaoParaTeste(Cliente cliente) {
		try {
			URL url = new URL("http://dls98:8181/captacao/api/clientes.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			OutputStream os = conn.getOutputStream();

			JsonObject json = new JsonObject();

			json.addProperty("id", cliente.getId());
			json.addProperty("nome", cliente.getNome());
			json.addProperty("email", cliente.getEmail());
			json.addProperty("cpf", cliente.getCpf());
			json.addProperty("dataNascimento", cliente.getDataNascimento()
					.toString());
			json.addProperty("celular", cliente.getCelular());

			os.write(json.toString().getBytes());
			os.flush();

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

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	
}
