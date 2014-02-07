package Utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import soa32.resources.produto.Produto;
import soa32.resources.produto.ProdutoResource;
import soa32.resources.produto.ProdutoResourcePortType;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ProdutoUtils {
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
	
	public static ArrayList<Object> jsonParaProduto(JsonArray lista) {
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
	
	public static String printaProduto(Produto p) {
		return p.getId() + "/" + p.getNome() + "/" + p.getPrecoDeCusto();		
	}
	
	public static ProdutoResourcePortType criaProdutoResourcePortType() {
		ProdutoResource p = new ProdutoResource();
		return p.getProdutoResourcePort();
	}
}
