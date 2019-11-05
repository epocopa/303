package integracion.cliente;

import integracion.DAO;
import negocio.TFecha;
import negocio.cliente.TCliente;

import java.util.List;

public interface ClienteDAO extends DAO<TCliente> {
	List<TCliente> listarClientesPorFecha(TFecha fecha) throws Exception;
}
