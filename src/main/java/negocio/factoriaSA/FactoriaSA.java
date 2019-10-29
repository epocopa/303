package negocio.factoriaSA;

import negocio.cliente.ClienteSA;
import negocio.factura.FacturaSA;
import negocio.producto.ProductoSA;

public abstract class FactoriaSA {
	private static FactoriaSA sa;
	
	public static FactoriaSA getInstancia() {
		if(sa == null) {
			sa = new FactoriaSAImp();
		}
		return sa;
	}
	
	public abstract ClienteSA generaClienteSA();
	public abstract FacturaSA generaFacturaSA();
	public abstract ProductoSA generaProductoSA();
}