package main.java.negocio.factoriaSA;


import main.java.negocio.cliente.ClienteSA;
import main.java.negocio.cliente.ClienteSAImp;
import main.java.negocio.factura.FacturaSA;
import main.java.negocio.factura.FacturaSAImp;
import main.java.negocio.producto.ProductoSA;
import main.java.negocio.producto.ProductoSAImp;

public class FactoriaSAImp extends FactoriaSA{
	
	public ClienteSA generaClienteSA(){
		return new ClienteSAImp();
	}
	
	public FacturaSA generaFacturaSA(){
		return new FacturaSAImp();
	}
	
	public ProductoSA generaProductoSA(){
		return new ProductoSAImp();
	}
}