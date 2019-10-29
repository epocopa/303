package negocio.factoriaSA;


import negocio.cliente.ClienteSA;
import negocio.cliente.ClienteSAImp;
import negocio.factura.FacturaSA;
import negocio.factura.FacturaSAImp;
import negocio.producto.ProductoSA;
import negocio.producto.ProductoSAImp;

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