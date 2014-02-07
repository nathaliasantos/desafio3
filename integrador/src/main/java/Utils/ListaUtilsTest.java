package Utils;

import junit.framework.TestCase;

import org.junit.Test;

import soa32.resources.cliente.Cliente;
import soa32.resources.produto.Produto;

public class ListaUtilsTest extends TestCase {

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
