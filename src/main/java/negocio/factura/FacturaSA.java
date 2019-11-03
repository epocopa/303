package negocio.factura;

import java.util.List;

public interface FacturaSA {
	boolean insertar(TFactura factura) throws Exception;
	TFactura mostrar(int id) throws Exception;
	List<TFactura> mostrarTodos() throws Exception;
	boolean modificar(TFactura factura);
	boolean eliminar(int id);

}
