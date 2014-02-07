package Utils;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

import soa32.resources.cliente.Cliente;
import soa32.resources.produto.Produto;

public class ListaUtilsTest extends TestCase {


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
