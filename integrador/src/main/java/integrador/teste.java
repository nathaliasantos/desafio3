package integrador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public static void main(String[] args) {
	NotaFiscalResource n = new NotaFiscalResource();
	NotaFiscalResourcePortType no = n.getNotaFiscalResourcePort();
	List<NotaFiscal> listaNota = no.list();
	System.out.println("listagem do numero das notas fiscais:");
	for (NotaFiscal notaFiscal : listaNota) {
		System.out.println(notaFiscal.getNumero());
	}
	
	ClienteResource c = new ClienteResource();
	ClienteResourcePortType cl = c.getClienteResourcePort();	
	List<Cliente> listaCliente = cl.list();
	System.out.println("listagem do nome dos clientes:");
	for (Cliente cliente : listaCliente) {
		System.out.println(cliente.getNome());
	}
	
	ProdutoResource p = new ProdutoResource();
	ProdutoResourcePortType pr = p.getProdutoResourcePort();
	ArrayList<Produto> listaProduto = (ArrayList<Produto>)pr.list();
	System.out.println("listagem do numero dos produtos:");
	for (Produto produto : listaProduto) {
		System.out.println(produto.getNome());
	}
	
	Interpretador.excluirProdutos("15");

}

}
