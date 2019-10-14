package integracion.FactoriaDAO;

import integracion.Cliente.ClienteDAO;
import integracion.Cliente.ClienteDAOImp;
import integracion.Factura.FacturaDAO;
import integracion.Factura.FacturaDAOImp;
import integracion.Producto.ProductoDAO;
import integracion.Producto.ProductoDAOImp;

public class FactoriaDAOImp extends FactoriaDAO{
	@Override
	public ClienteDAO getClienteDAO() {
		return new ClienteDAOImp();
	}

	@Override
	public FacturaDAO getFacturaDAO() {
		return new FacturaDAOImp();
	}

	@Override
	public ProductoDAO getProductoDAO() {
		return new ProductoDAOImp();
	}
}
