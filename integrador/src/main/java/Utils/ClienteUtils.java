package Utils;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import soa32.resources.cliente.Cliente;
import soa32.resources.cliente.ClienteResource;
import soa32.resources.cliente.ClienteResourcePortType;

public class ClienteUtils {
	public static void adicionarNovosClientes(ArrayList<Cliente> clientesNovos) {
		ClienteResource c = new ClienteResource();
		ClienteResourcePortType cl = c.getClienteResourcePort();
		for (Cliente cliente : clientesNovos) {
			System.out.println("CRIAR: " + cliente.getDataNascimento());
			cl.create(cliente);
		}
	}

	public static ArrayList<Object> jsonArrayParaListaCliente(JsonArray lista) {
		ArrayList<Object> listaClientes = new ArrayList<Object>();
		for (int i = 0; i < lista.size(); i++) {
			JsonObject objetoAtual = lista.get(i).getAsJsonObject();
			Cliente novoCliente = criaClientePeloJson(objetoAtual);
			listaClientes.add(novoCliente);
		}

		return listaClientes;
	}

	private static Cliente criaClientePeloJson(JsonObject objetoAtual) {
		Cliente novoCliente = new Cliente();
		novoCliente.setId(Long.parseLong(objetoAtual.get("id").getAsString()));
		novoCliente.setCelular(objetoAtual.get("celular").getAsString());
		novoCliente.setCpf(objetoAtual.get("cpf").getAsString());
		novoCliente.setEmail(objetoAtual.get("email").getAsString());
		novoCliente.setNome(objetoAtual.get("nome").getAsString());

		XMLGregorianCalendar date = stringParaXMLGregorianDate(objetoAtual.get(
				"dataNascimento").getAsString());

		novoCliente.setDataNascimento(date);
		return novoCliente;
	}

	private static XMLGregorianCalendar stringParaXMLGregorianDate(String data) {
		String[] split = data.split("T")[0].split("-");

		XMLGregorianCalendar date = null;
		try {
			date = DatatypeFactory.newInstance().newXMLGregorianCalendar(
					new GregorianCalendar(Integer.parseInt(split[0]), Integer
							.parseInt(split[1]), Integer.parseInt(split[2])));
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String printaCliente(Cliente c) {
		return c.getId() + "/" + c.getCelular() + "/" + c.getCpf() + "/"
				+ c.getEmail() + "/" + c.getNome() + "/"
				+ c.getDataNascimento();
	}

	public static ClienteResourcePortType criaClienteResourcePortType() {
		ClienteResource c = new ClienteResource();
		return c.getClienteResourcePort();
	}

}
