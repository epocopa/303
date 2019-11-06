package negocio.cliente;

import integracion.factoriaDAO.FactoriaDAO;
import integracion.transaction.Transaction;
import integracion.transactionManager.TransactionManager;
import negocio.TFecha;

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

		if (c != null) {
			FactoriaDAO.getInstancia().getClienteDAO().modificar(cliente);
			t.commit();
			TransactionManager.getInstancia().removeTransaction();
		} else {
			throw  new Exception("No existe ningun cliente con ID =" + cliente.getId());
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
		} else if (c == null){
			throw  new Exception("No existe ningun cliente con ID =" + id);
		} else {
			throw  new Exception("El cliente seleccionado no esta activo");
		}
	}

	public List<TCliente> listarClientesPorFecha(TFecha fecha) throws Exception {
		Transaction t = TransactionManager.getInstancia().createTransaction();
		if (fecha.getFechaIni().isAfter(fecha.getFechaFin())) {
			throw  new Exception("La fecha inicial es posterior a la fecha final");
		}
		List<TCliente> lista = FactoriaDAO.getInstancia().getClienteDAO().listarClientesPorFecha(fecha);
		t.commit();
		TransactionManager.getInstancia().removeTransaction();
		return lista;
	}
}
