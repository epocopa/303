package negocio.cliente;

import java.util.List;

public interface ClienteSA {
	boolean insertar(TCliente cliente) throws Exception;
	TCliente mostrar(int id) throws Exception;
	List<TCliente> mostrarTodos() throws Exception;
	boolean modificar(TCliente cliente);
	boolean eliminar(int id);
}
