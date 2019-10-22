package negocio.factoriaSA;

import negocio.negocioCliente.ClienteSA;
import negocio.negocioCliente.ClienteSAImpl;
import negocio.negocioFactura.FacturaSA;
import negocio.negocioFactura.FacturaSAImpl;
import negocio.negocioProducto.ProductoSA;
import negocio.negocioProducto.ProductoSAImpl;

public class FactoriaSAImpl extends FactoriaSA{
	
	public SACliente generaClienteSA(){
		return new ClienteSAImpl();
	}
	
	public SAFactura generaFacturaSA(){
		return new FacturaSAImpl();
	}
	
	public SAProducto generaProductoSA(){
		return new ProductoSAImpl();
	}
}