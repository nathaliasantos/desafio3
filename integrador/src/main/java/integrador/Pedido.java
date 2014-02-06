package integrador;

import java.util.List;

import soa32.resources.notaFiscal.Item;

public class Pedido {
	private Long id;
	private Long notaFiscal;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNotaFiscal() {
		return notaFiscal;
	}
	public void setNotaFiscal(Long notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	public Long getLote() {
		return lote;
	}
	public void setLote(Long lote) {
		this.lote = lote;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	public Object getNotaFiscalLink() {
		return notaFiscalLink;
	}
	public void setNotaFiscalLink(Object notaFiscalLink) {
		this.notaFiscalLink = notaFiscalLink;
	}
	public Object getLoteLink() {
		return loteLink;
	}
	public void setLoteLink(Object loteLink) {
		this.loteLink = loteLink;
	}
	private Long lote;
	private String status;
	private List<Item> itens;
	private Object notaFiscalLink;
	private Object loteLink;
}
