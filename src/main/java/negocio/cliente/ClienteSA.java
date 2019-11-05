package negocio.cliente;

import negocio.TFecha;

import java.util.List;

public interface ClienteSA {
	void insertar(TCliente cliente) throws Exception;
	TCliente mostrar(int id) throws Exception;
	List<TCliente> mostrarTodos() throws Exception;
	void modificar(TCliente cliente) throws Exception;
	void eliminar(int id) throws Exception;
	List<TCliente> listarClientesPorFecha(TFecha fecha) throws Exception;
}
