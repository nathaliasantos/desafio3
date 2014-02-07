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
		c1.setId(1L);
		
		Cliente c2 = new Cliente();
		c2.setId(2L);
		
		Cliente c3 = new Cliente();
		c3.setId(3L);
		
		Cliente c4 = new Cliente();
		c4.setId(4L);
		
		Cliente c5 = new Cliente();
		c5.setId(5L);
		
		Cliente c6 = new Cliente();
		c6.setId(6L);
		
		Cliente c7 = new Cliente();
		c7.setId(7L);
		
		Cliente c8 = new Cliente();
		c8.setId(8L);
		
		Cliente c9 = new Cliente();
		c9.setId(9L);
		
		Cliente c10 = new Cliente();
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
		
		p1.setId((long)1337);
		p2.setId((long)1338);
		p3.setId((long)1339);
		p4.setId((long)1340);
		p5.setId((long)1341);
		p6.setId((long)1342);
				
		
		ArrayList<Produto> novo = new ArrayList<Produto>();
		ArrayList<Produto> antigo = new ArrayList<Produto>();
		
		antigo.add(p1);
		antigo.add(p2);
		antigo.add(p3);
		antigo.add(p4);

		novo.add(p1);
		novo.add(p2);
		novo.add(p3);
		novo.add(p4);
		novo.add(p5);
		novo.add(p6);	
		
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
