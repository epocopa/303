package integracion.FactoriaDAO;

import integracion.Cliente.ClienteDAO;
import integracion.Factura.FacturaDAO;
import integracion.Producto.ProductoDAO;

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
