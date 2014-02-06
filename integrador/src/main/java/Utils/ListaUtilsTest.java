package Utils;

import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;

import org.junit.Test;

public class ListaUtilsTest extends TestCase {

	@Test
	public void testeDiferenca() {
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();

		list1.addAll(Arrays.asList(2, 3, 5, 8, 13, 21));
		list2.addAll(Arrays.asList(1, 2, 3, 5, 8, 13));
		ArrayList<Integer> list3 = new ArrayList<Integer>();
		list3.add(21);

		assertEquals(list3, ListaUtils.diferenca(list1, list2));
	}

	@Test
	public void testeInterseccao() {
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();

		list1.addAll(Arrays.asList(1, 2, 3, 5, 8, 13, 21));
		list2.addAll(Arrays.asList(1, 2, 3, 5, 8, 13));

		assertEquals(list2, ListaUtils.interseccao(list1, list2));
	}
	
	
	@Test
	public void testeAdicionar(){
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();

		list1.addAll(Arrays.asList(1, 2, 3, 5, 8, 13, 21));
		list2.addAll(Arrays.asList(1, 2, 3, 5, 8, 13));
		ArrayList<Integer> list3 = new ArrayList<Integer>();
		list3.add(21);
				
		assertEquals(list3, ListaUtils.listaAdicionar(list2, list1));
	}
	
	
	@Test
	public void testeRemover(){
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();

		list1.addAll(Arrays.asList(1, 2, 3, 5, 8, 13, 21));
		list2.addAll(Arrays.asList(1, 2, 3, 5, 8, 13));
		ArrayList<Integer> list3 = new ArrayList<Integer>();
		list3.add(21);
				
		assertEquals(list3, ListaUtils.listaDeletar(list1, list2));
	}
}
