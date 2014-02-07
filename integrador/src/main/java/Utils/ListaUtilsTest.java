package Utils;

import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;

import org.junit.Test;

import soa32.resources.cliente.Cliente;
import soa32.resources.produto.Produto;

public class ListaUtilsTest extends TestCase {
	@Test
	public void testeListaAdicionarCliente(){
		
		Cliente c1 = new Cliente();
		Cliente c2 = new Cliente();
		Cliente c3 = new Cliente();
		Cliente c4 = new Cliente();
		Cliente c5 = new Cliente();
		Cliente c6 = new Cliente();		
		Cliente c7 = new Cliente();
		Cliente c8 = new Cliente();
		Cliente c9 = new Cliente();
		Cliente c10 = new Cliente();
		
		c1.setId(1L);
		c2.setId(2L);
		c3.setId(3L);
		c4.setId(4L);
		c5.setId(5L);
		c6.setId(6L);
		c7.setId(7L);
		c8.setId(8L);
		c9.setId(9L);
		c10.setId(10L);
		
		ArrayList<Cliente> nova = new ArrayList<Cliente>();
		nova.addAll(Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10));
		
		ArrayList<Cliente> antiga = new ArrayList<Cliente>();
		antiga.addAll(Arrays.asList(c1,c2,c3,c4,c5,c6,c7));
		
		ArrayList<Cliente> result = new ArrayList<Cliente>();
		result.addAll(Arrays.asList(c8,c9,c10));

		//inserções diversas
		assertEquals(ListaUtils.listaAdicionarCliente(nova, antiga), result);
		
		//sem alterações
		nova = antiga;
		result.clear();		
		assertEquals(ListaUtils.listaAdicionarCliente(nova, antiga), result);
	}


	@Test
	public void testeProdutoInserido(){
		Produto p1 = new Produto();
		Produto p2 = new Produto();
		Produto p3 = new Produto();
		Produto p4 = new Produto();
		Produto p5 = new Produto();
		Produto p6 = new Produto();		
		
		p1.setId(1337L);
		p2.setId(1338L);
		p3.setId(1339L);
		p4.setId(1340L);
		p5.setId(1341L);
		p6.setId(1342L);
				
		
		ArrayList<Produto> novo = new ArrayList<Produto>();
		ArrayList<Produto> antigo = new ArrayList<Produto>();
		
		antigo.addAll(Arrays.asList(p1,p2,p3,p4));
		novo.addAll(Arrays.asList(p1,p2,p3,p4,p5,p6));

		
		ArrayList<Produto> aAdicionar = new ArrayList<Produto>();
		
		aAdicionar.add(p5);
		aAdicionar.add(p6);
		
		ArrayList<Produto> retornado = ListaUtils.listaAdicionarProduto(novo, antigo);
		
		assertEquals(aAdicionar, retornado);
		
	}

	@Test 
	public void testeClienteIgual() {
		Produto p1 = new Produto();
		p1.setId((long)1);
		Produto p2 = new Produto();
		p2.setId((long)1);
		assertTrue(ListaUtils.igual(p1, p2));
	}
	
	@Test 
	public void testeProdutoIgual() {
		Cliente c1 = new Cliente();
		c1.setId((long)1);
		Cliente c2 = new Cliente();
		c2.setId((long)1);
		assertTrue(ListaUtils.igual(c1, c2));
	}
}
