package integracion.factura;

import integracion.DAO;
import negocio.TFecha;
import negocio.factura.TFactura;
import negocio.factura.TOAProductoFactura;

import java.util.List;

public interface FacturaDAO extends DAO<TFactura> {
	void anadirProducto(TOAProductoFactura toa) throws Exception;
	void borrarProducto(TOAProductoFactura toa) throws Exception;
	List<TFactura> listarProductosPorFecha(TFecha fecha) throws Exception;
}
