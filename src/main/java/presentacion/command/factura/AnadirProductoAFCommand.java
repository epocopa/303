package presentacion.command.factura;

import negocio.factoriaSA.FactoriaSA;
import negocio.factura.FacturaSA;
import negocio.factura.TLineaFactura;
import negocio.producto.TProducto;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosFactura;

public class AnadirProductoAFCommand implements Command{

	@Override
	public Context execute(Object datos) {
		String mensaje;
		TLineaFactura factura = (TLineaFactura) datos;
		FacturaSA facturaSA = FactoriaSA.getInstancia().generaFacturaSA();
		
		try{
			TProducto producto = facturaSA.añadirProducto(factura);
			return new Context(EventosFactura.ANADIR_PRODUCTO_A_F_OK, producto);
		} catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosFactura.ANADIR_PRODUCTO_A_F_KO, mensaje);
		}
	}
}