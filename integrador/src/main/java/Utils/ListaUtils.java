package Utils;
import java.util.ArrayList;

import soa32.resources.cliente.Cliente;
import soa32.resources.produto.Produto;

public class ListaUtils {
	public static boolean igual(Cliente a, Cliente b) {
		return a.getId() == b.getId();
	}

	public static ArrayList<Cliente> listaAdicionarCliente(ArrayList<Cliente> nova,
			ArrayList<Cliente> antiga) {
		ArrayList<Cliente> list = new ArrayList<Cliente>();
		for (Cliente a : nova) {
			boolean existe = false;
			for (Cliente b : antiga) {
				if (igual(a, b))
					existe = true;
			}
			if (!existe)
				list.add(a);
		}
		return list;
	}
	
	public static boolean igual(Produto a, Produto b) {
		return a.getId() == b.getId();
	}
	
	public static ArrayList<Produto> listaAdicionarProduto(ArrayList<Produto> nova,
			ArrayList<Produto> antiga) {
		ArrayList<Produto> list = new ArrayList<Produto>();
		for (Produto a : nova) {
			boolean existe = false;
			for (Produto b : antiga) {
				if (igual(a, b))
					existe = true;
			}
			if (!existe)
				list.add(a);
		}
		return list;
	}
}