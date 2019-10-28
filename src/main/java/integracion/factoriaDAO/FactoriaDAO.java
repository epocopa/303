package main.java.integracion.factoriaDAO;

import main.java.integracion.cliente.ClienteDAO;
import main.java.integracion.factura.FacturaDAO;
import main.java.integracion.producto.ProductoDAO;

public abstract class FactoriaDAO {
	private static FactoriaDAO instancia = null;

	public abstract ClienteDAO getClienteDAO();

	public abstract FacturaDAO getFacturaDAO();

	public abstract ProductoDAO getProductoDAO();

	public static FactoriaDAO getInstancia() {
		if (instancia == null) {
			instancia = new FactoriaDAOImp();
		}
		return instancia;
	}
}
