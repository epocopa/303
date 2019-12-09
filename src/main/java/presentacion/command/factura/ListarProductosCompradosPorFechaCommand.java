package presentacion.command.factura;

import java.util.List;

import negocio.TFecha;
import negocio.factoriaSA.FactoriaSA;
import negocio.factura.FacturaSA;
import negocio.factura.TFactura;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosFactura;

public class ListarProductosCompradosPorFechaCommand implements Command {

	@Override
	public Context execute(Object datos) {
		String mensaje;
		FacturaSA facturaSA = FactoriaSA.getInstancia().generaFacturaSA();
		TFecha productosFecha = (TFecha) datos;

		try {
			List<TFactura> listaProductos = facturaSA
					.listarProductosPorFecha(productosFecha);
			return new Context(
					EventosFactura.LISTAR_PRODUCTOS_COMPRADOS_POR_FECHA_OK,
					listaProductos);
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new Context(
					EventosFactura.LISTAR_PRODUCTOS_COMPRADOS_POR_FECHA_KO,
					mensaje);
		}
	}
}