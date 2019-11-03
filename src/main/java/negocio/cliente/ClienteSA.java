package negocio.cliente;

import java.util.List;

public interface ClienteSA {
	boolean insertar(TCliente cliente) throws Exception;
	TCliente mostrar(int id);
	List<TCliente> mostrarTodos();
	boolean modificar(TCliente cliente);
	boolean eliminar(int id);
}
