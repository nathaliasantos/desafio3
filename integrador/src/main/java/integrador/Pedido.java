package integrador;

import java.util.List;

import soa32.resources.notaFiscal.Item;

public class Pedido {
	public Long id;
	public Long notaFiscal;
	public Long lote;
	public String status;
	public List<Item> itens;
	public Object notaFiscalLink;
	public Object loteLink;
}
