package Utils;

import soa32.resources.notaFiscal.NotaFiscalResource;
import soa32.resources.notaFiscal.NotaFiscalResourcePortType;

public class NotaFiscalUtils {
	public static NotaFiscalResourcePortType criaNotaFiscalResourcePortType() {
		NotaFiscalResource n = new NotaFiscalResource();
		return n.getNotaFiscalResourcePort();
	}
}
