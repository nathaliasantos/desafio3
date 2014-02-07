package Utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import soa32.resources.produto.Produto;
import soa32.resources.produto.ProdutoResource;
import soa32.resources.produto.ProdutoResourcePortType;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ProdutoUtils {
	public static void adicionarNovosProdutos(ArrayList<Produto> novosProdutos) {
		if (novosProdutos.size() > 0)
		try {
			URL url = new URL("http://dls98:8181/captacao/api/produtos.json");
			HttpURLConnection conn = criaConexao(url);
			OutputStream os = conn.getOutputStream();

			for (Produto produto : novosProdutos) {
				JsonObject json = pedidoParaJson(produto);
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

	private static HttpURLConnection criaConexao(URL url) throws IOException,
			ProtocolException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		return conn;
	}

	private static JsonObject pedidoParaJson(Produto produto) {
		JsonObject json = new JsonObject();
		json.addProperty("id", produto.getId());
		json.addProperty("nome", produto.getNome());
		json.addProperty("departamento", produto.getDepartamento());
		json.addProperty("fabricante", produto.getFabricante());
		json.addProperty("precoDeCusto", produto.getPrecoDeCusto());
		json.addProperty("dataValidade", produto.getDataValidade());
		json.addProperty("tamanho", produto.getTamanho());
		json.addProperty("urlImage", produto.getUrlImage());
		json.addProperty("itemExclusivo", produto.isItemExclusivo());
		return json;
	}

	public static ArrayList<Object> jsonArrayParaListaProduto(JsonArray lista) {
		ArrayList<Object> listaProdutos = new ArrayList<Object>();
		for (int i = 0; i < lista.size(); i++) {
			JsonObject objetoAtual = lista.get(i).getAsJsonObject();
			Produto novoProduto = criaProdutoPeloJson(objetoAtual);
			listaProdutos.add(novoProduto);
		}

		return listaProdutos;
	}

	private static Produto criaProdutoPeloJson(JsonObject objetoAtual) {
		Produto novoProduto = new Produto();
		if (objetoAtual.get("id") != null)
			novoProduto.setId(Long.parseLong(objetoAtual.get("id")
					.getAsString()));
		if (objetoAtual.get("nome") != null)
			novoProduto.setNome(objetoAtual.get("nome").getAsString());
		if (objetoAtual.get("departamento") != null)
			novoProduto.setDepartamento(objetoAtual.get("departamento")
					.getAsString());
		if (objetoAtual.get("fabricante") != null)
			novoProduto.setFabricante(objetoAtual.get("fabricante")
					.getAsString());
		if (objetoAtual.get("tamanho") != null)
			novoProduto.setTamanho(objetoAtual.get("tamanho").getAsString());
		if (objetoAtual.get("urlImage") != null)
			novoProduto.setUrlImage(objetoAtual.get("urlImage").getAsString());
		if (objetoAtual.get("itemExclusivo") != null)
			novoProduto.setItemExclusivo(new Boolean(objetoAtual.get(
					"itemExclusivo").getAsString()));
		if (objetoAtual.get("dataValidade") != null)
			novoProduto.setDataValidade(objetoAtual.get("dataValidade")
					.getAsString());
		if (objetoAtual.get("precoDeCusto") != null)
			novoProduto.setPrecoDeCusto(Long.parseLong(objetoAtual.get(
					"precoDeCusto").getAsString()));
		return novoProduto;
	}

	public static String printaProduto(Produto p) {
		return p.getId() + "/" + p.getNome() + "/" + p.getPrecoDeCusto();
	}

	public static ProdutoResourcePortType criaProdutoResourcePortType() {
		ProdutoResource p = new ProdutoResource();
		return p.getProdutoResourcePort();
	}
}
