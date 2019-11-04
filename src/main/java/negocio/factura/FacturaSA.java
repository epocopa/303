package negocio.factura;

import java.util.List;

public interface FacturaSA {
	void insertar(TFactura factura) throws Exception;
	TFactura mostrar(int id) throws Exception;
	List<TFactura> mostrarTodos() throws Exception;
	void modificar(TFactura factura);
	void eliminar(int id);
}
