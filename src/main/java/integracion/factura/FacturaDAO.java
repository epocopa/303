package integracion.factura;

import integracion.DAO;
import negocio.TFecha;
import negocio.factura.TFactura;
import negocio.factura.TLineaFactura;
import negocio.producto.TProducto;

import java.util.List;

public interface FacturaDAO extends DAO<TFactura> {
	void anadirProducto(TLineaFactura lineaFactura, TProducto p) throws Exception;
	void borrarProducto(TLineaFactura lineaFactura, TProducto p) throws Exception;
	List<TFactura> listarProductosPorFecha(TFecha fecha) throws Exception;
}
