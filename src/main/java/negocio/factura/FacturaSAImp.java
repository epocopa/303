package negocio.factura;

import integracion.factoriaDAO.FactoriaDAO;
import integracion.transaction.Transaction;
import integracion.transactionManager.TransactionManager;
import negocio.TFecha;
import negocio.cliente.TCliente;
import negocio.producto.TProducto;

import java.util.List;

public class FacturaSAImp implements FacturaSA {
	
	@Override
	public void insertar(TFactura factura) throws Exception {
		Transaction t = TransactionManager.getInstancia().createTransaction();
		TCliente c = FactoriaDAO.getInstancia().getClienteDAO().mostrar(factura.getCliente());
		if (c != null && c.isActivo()) {
			FactoriaDAO.getInstancia().getFacturaDAO().insertar(factura);
			t.commit();
			TransactionManager.getInstancia().removeTransaction();
		} else if(c == null){
			throw  new Exception("No existe ningun cliente con ID =" + factura.getCliente());
		} else if(!c.isActivo()){
			throw  new Exception("El cliente seleccionado no esta activo");
		}
	}

	@Override
	public TFactura mostrar(int id) throws Exception {
		Transaction t = TransactionManager.getInstancia().createTransaction();
		TFactura f = FactoriaDAO.getInstancia().getFacturaDAO().mostrar(id);
		t.commit();
		TransactionManager.getInstancia().removeTransaction();
		if (f == null) {
			throw  new Exception("No existe ninguna factura con ID =" + id);
		}
		return f;
	}

	@Override
	public List<TFactura> mostrarTodos() throws Exception {
		Transaction t = TransactionManager.getInstancia().createTransaction();
		List<TFactura> lista = FactoriaDAO.getInstancia().getFacturaDAO().mostrarTodos();
		t.commit();
		TransactionManager.getInstancia().removeTransaction();
		return lista;
	}

	@Override
	public void modificar(TFactura factura) throws Exception {
		throw new Exception("Operation not suported-> Error");
	}

	@Override
	public void eliminar(int id) throws Exception {
		Transaction t = TransactionManager.getInstancia().createTransaction();
		TFactura f = FactoriaDAO.getInstancia().getFacturaDAO().mostrar(id);

		if (f != null && f.isAbierta()) {
			FactoriaDAO.getInstancia().getFacturaDAO().eliminar(id);
			t.commit();
			TransactionManager.getInstancia().removeTransaction();
		} else if (f == null){
			throw  new Exception("No existe ningun cliente con ID =" + id);
		} else {
			throw  new Exception("El cliente seleccionado no esta activo");
		}
	}

	@Override
	public void anadirProducto(TLineaFactura lineaFactura) throws Exception {
		Transaction t = TransactionManager.getInstancia().createTransaction();
		TFactura f = FactoriaDAO.getInstancia().getFacturaDAO().mostrar(lineaFactura.getFactura());
		TProducto p = FactoriaDAO.getInstancia().getProductoDAO().mostrar(lineaFactura.getProducto());

		if (f == null) {
			throw new Exception("La factura no existe-> Error");
		} else if (!f.isAbierta()) {
			throw new Exception("La factura esta cerrada -> Error");
		} else if (p == null) {
			throw new Exception("El producto no existe-> Error");
		} else if (!p.isActivo()) {
			throw new Exception("El producto no esta activo-> Error");
		} else if(lineaFactura.getCantidad() > p.getCantidad()) {
			throw new Exception("El producto no tiene suficiente stock -> Error");
		} else if(lineaFactura.getCantidad() <= 0) {
			throw new Exception("La cantidad es <= 0 -> Error");
		} else {
			FactoriaDAO.getInstancia().getFacturaDAO().anadirProducto(new TOAProductoFactura(lineaFactura, p));
			t.commit();
			TransactionManager.getInstancia().removeTransaction();
		}
	}

	@Override
	public void borrarProducto(TLineaFactura lineaFactura) throws Exception {
		Transaction t = TransactionManager.getInstancia().createTransaction();
		TFactura f = FactoriaDAO.getInstancia().getFacturaDAO().mostrar(lineaFactura.getFactura());
		TProducto p = FactoriaDAO.getInstancia().getProductoDAO().mostrar(lineaFactura.getProducto());

		if (f == null) {
			throw new Exception("La factura no existe-> Error");
		} else if (!f.isAbierta()) {
			throw new Exception("La factura esta cerrada -> Error");
		} else if (p == null) {
			throw new Exception("El producto no existe-> Error");
		} else if (!p.isActivo()) {
			throw new Exception("El producto no esta activo-> Error");
		} else if(lineaFactura.getCantidad() <= 0) {
			throw new Exception("La cantidad es <= 0 -> Error");
		} else {
			FactoriaDAO.getInstancia().getFacturaDAO().borrarProducto(new TOAProductoFactura(lineaFactura, p));
			t.commit();
			TransactionManager.getInstancia().removeTransaction();
		}
	}

	@Override
	public List<TFactura> listarProductosPorFecha(TFecha fecha) throws Exception {
		Transaction t = TransactionManager.getInstancia().createTransaction();
		if (fecha.getFechaIni().isAfter(fecha.getFechaFin())) {
			throw  new Exception("La fecha inicial es posterior a la fecha final");
		}
		List<TFactura> lista = FactoriaDAO.getInstancia().getFacturaDAO().listarProductosPorFecha(fecha);
		t.commit();
		TransactionManager.getInstancia().removeTransaction();
		return lista;
	}
}
