package negocio.cliente;

import integracion.factoriaDAO.FactoriaDAO;
import integracion.transaction.Transaction;
import integracion.transactionManager.TransactionManager;

import java.util.List;

public class ClienteSAImp implements ClienteSA {

	@Override
	public void insertar(TCliente cliente) throws Exception {
		Transaction t = TransactionManager.getInstancia().createTransaction();
		FactoriaDAO.getInstancia().getClienteDAO().insertar(cliente);
		t.commit();
		TransactionManager.getInstancia().removeTransaction();
	}

	@Override
	public TCliente mostrar(int id) throws Exception {
		Transaction t = TransactionManager.getInstancia().createTransaction();
		TCliente c = FactoriaDAO.getInstancia().getClienteDAO().mostrar(id);
		t.commit();
		TransactionManager.getInstancia().removeTransaction();
		if (c == null) {
			throw  new Exception("No existe ningun cliente con ID =" + id);
		}
		return c;
	}

	@Override
	public List<TCliente> mostrarTodos() throws Exception {
		Transaction t = TransactionManager.getInstancia().createTransaction();
		List<TCliente> lista = FactoriaDAO.getInstancia().getClienteDAO().mostrarTodos();
		t.commit();
		TransactionManager.getInstancia().removeTransaction();
		return lista;
	}

	@Override
	public void modificar(TCliente cliente) throws Exception {
		Transaction t = TransactionManager.getInstancia().createTransaction();
		TCliente c = FactoriaDAO.getInstancia().getClienteDAO().mostrar(cliente.getId());

		if (c != null && c.isActivo()) {
			FactoriaDAO.getInstancia().getClienteDAO().modificar(c);
			t.commit();
			TransactionManager.getInstancia().removeTransaction();
		} else if (c != null){
			throw  new Exception("No existe ningun cliente con ID =" + c.getId());
		} else {
			throw  new Exception("El cliente seleccionado no esta activo");
		}
	}

	@Override
	public void eliminar(int id) throws Exception {
		Transaction t = TransactionManager.getInstancia().createTransaction();
		TCliente c = FactoriaDAO.getInstancia().getClienteDAO().mostrar(id);

		if (c != null && c.isActivo()) {
			FactoriaDAO.getInstancia().getClienteDAO().eliminar(id);
			t.commit();
			TransactionManager.getInstancia().removeTransaction();
		} else if (c != null){
			throw  new Exception("No existe ningun cliente con ID =" + c.getId());
		} else {
			throw  new Exception("El cliente seleccionado no esta activo");
		}
	}
}
