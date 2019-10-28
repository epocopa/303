package main.java.negocio.factoriaSA;

import main.java.negocio.cliente.ClienteSA;
import main.java.negocio.factura.FacturaSA;
import main.java.negocio.producto.ProductoSA;

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