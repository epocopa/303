package negocio.factoriaSA;

import negocio.negocioCliente.ClienteSA;
import negocio.negocioFactura.FacturaSA;
import negocio.negocioProducto.ProductoSA;

public abstract class FactoriaSA {
	private static FactoriaSA sa;
	
	public static FactoriaSA getInstancia() {
		if(sa == null) {
			sa = new FactoriaSAImpl();
		}
		return sa;
	}
	
	public abstract ClienteSA generaClienteSA();
	public abstract FacturaSA generaFacturaSA();
	public abstract ProductoSA generaProductoSA();
}