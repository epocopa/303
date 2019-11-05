package negocio.factura;

import negocio.TFecha;

import java.util.List;

public interface FacturaSA {
	void insertar(TFactura factura) throws Exception;
	TFactura mostrar(int id) throws Exception;
	List<TFactura> mostrarTodos() throws Exception;
	void modificar(TFactura factura) throws Exception;
	void eliminar(int id) throws Exception;
	void anadirProducto(TLineaFactura lineaFactura) throws Exception;
	void borrarProducto(TLineaFactura lineaFactura) throws Exception;
	List<TFactura> listarProductosPorFecha(TFecha fecha) throws Exception;
}
