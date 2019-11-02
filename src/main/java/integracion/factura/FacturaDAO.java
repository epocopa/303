package integracion.factura;

import integracion.DAO;
import negocio.factura.TFactura;
import negocio.factura.TLineaFactura;
import negocio.producto.TProducto;

public interface FacturaDAO extends DAO<TFactura> {
	void anadirProducto(TLineaFactura lineaFactura, TProducto p) throws Exception;
}
