package integracion.factoriaDAO;

import integracion.cliente.ClienteDAO;
import integracion.cliente.ClienteDAOImp;
import integracion.factura.FacturaDAO;
import integracion.factura.FacturaDAOImp;
import integracion.producto.ProductoDAO;
import integracion.producto.ProductoDAOImp;

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
