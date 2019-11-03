package negocio.cliente;

import integracion.factoriaDAO.FactoriaDAO;
import integracion.transaction.Transaction;
import integracion.transactionManager.TransactionManager;

import java.util.ArrayList;
import java.util.List;

public class ClienteSAImp implements ClienteSA {

	@Override
	public boolean insertar(TCliente cliente) throws Exception {
		Transaction t = TransactionManager.getInstancia().createTransaction();
		FactoriaDAO.getInstancia().getClienteDAO().insertar(cliente);
		t.commit();
		TransactionManager.getInstancia().removeTransaction();
		return true;
	}

	@Override
	public TCliente mostrar(int id) throws Exception {
		//TODO si no existe lanza excepcion?
		//TODO Hace falta abrir una transaccion para leer?
		return FactoriaDAO.getInstancia().getClienteDAO().mostrar(id);
	}

	@Override
	public List<TCliente> mostrarTodos() throws Exception {
		return FactoriaDAO.getInstancia().getClienteDAO().mostrarTodos();
	}

	@Override
	public boolean modificar(TCliente cliente) {
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		return false;
	}
}
