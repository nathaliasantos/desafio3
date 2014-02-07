package Utils;

import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;

import org.junit.Test;

import soa32.resources.cliente.Cliente;

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
	
}
