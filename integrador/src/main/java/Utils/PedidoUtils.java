package Utils;

import integrador.Pedido;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class PedidoUtils {
	public static ArrayList<Object> jsonArrayParaListaPedido(JsonArray lista) {
		ArrayList<Object> listaPedidos = new ArrayList<Object>();
		for (int i = 0; i < lista.size(); i++) {
			JsonObject objetoAtual = lista.get(i).getAsJsonObject();
			Pedido novoPedido = criaNovoPedidoPeloJson(objetoAtual);
			listaPedidos.add(novoPedido);
		}
		return listaPedidos;
	}

	private static Pedido criaNovoPedidoPeloJson(JsonObject objetoAtual) {
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
		return novoPedido;
	}
}
