package negocio.cliente;

import integracion.factoriaDAO.FactoriaDAO;
import integracion.transaction.Transaction;
import integracion.transactionManager.TransactionManager;

import java.util.List;

public class ClienteSAImp implements ClienteSA {

	@Override
	public boolean insertar(TCliente cliente) throws Exception {
		Transaction t = TransactionManager.getInstancia().createTransaction();
		FactoriaDAO.getInstancia().getClienteDAO().insertar(cliente);
		return true;
	}

	@Override
	public TCliente mostrar(int id) {
		return null;
	}

	@Override
	public List<TCliente> mostrarTodos() {
		return null;
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
