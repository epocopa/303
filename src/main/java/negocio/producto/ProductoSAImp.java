package negocio.producto;

import integracion.factoriaDAO.FactoriaDAO;
import integracion.transaction.Transaction;
import integracion.transactionManager.TransactionManager;

import java.util.List;

public class ProductoSAImp implements ProductoSA {
	@Override
	public void insertar(TProducto producto) throws Exception {
		Transaction t = TransactionManager.getInstancia().createTransaction();
		TProducto p = FactoriaDAO.getInstancia().getProductoDAO().mostrarPorNombre(producto.getNombre());

		if (p == null) {
			FactoriaDAO.getInstancia().getProductoDAO().insertar(producto);
			t.commit();
			TransactionManager.getInstancia().removeTransaction();
		} else {
			throw new Exception("Ya existe un producto con nombre =" + producto.getNombre());
		}
	}

	@Override
	public TProducto mostrar(int id) throws Exception {
		Transaction t = TransactionManager.getInstancia().createTransaction();
		TProducto p = FactoriaDAO.getInstancia().getProductoDAO().mostrar(id);
		t.commit();
		TransactionManager.getInstancia().removeTransaction();
		if (p == null) {
			throw new Exception("No existe ningun producto con ID =" + id);
		}
		return p;
	}

	@Override
	public List<TProducto> mostrarTodos() throws Exception {
		Transaction t = TransactionManager.getInstancia().createTransaction();
		List<TProducto> lista = FactoriaDAO.getInstancia().getProductoDAO().mostrarTodos();
		t.commit();
		TransactionManager.getInstancia().removeTransaction();
		return lista;
	}

	@Override
	public void modificar(TProducto producto) throws Exception {
		Transaction t = TransactionManager.getInstancia().createTransaction();
		TProducto p = FactoriaDAO.getInstancia().getProductoDAO().mostrar(producto.getId());

		if (p != null) {
			FactoriaDAO.getInstancia().getProductoDAO().modificar(producto);
			t.commit();
			TransactionManager.getInstancia().removeTransaction();
		} else {
			throw new Exception("No existe ningun producto con ID =" + producto.getId());
		}
	}

	@Override
	public void eliminar(int id) throws Exception {
		Transaction t = TransactionManager.getInstancia().createTransaction();
		TProducto c = FactoriaDAO.getInstancia().getProductoDAO().mostrar(id);

		if (c != null && c.isActivo()) {
			FactoriaDAO.getInstancia().getProductoDAO().eliminar(id);
			t.commit();
			TransactionManager.getInstancia().removeTransaction();
		} else if (c == null) {
			throw new Exception("No existe ningun producto con ID =" + id);
		} else {
			throw new Exception("El producto seleccionado no esta activo");
		}
	}
}
