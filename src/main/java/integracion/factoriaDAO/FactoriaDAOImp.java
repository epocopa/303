package main.java.integracion.factoriaDAO;

import main.java.integracion.cliente.ClienteDAO;
import main.java.integracion.cliente.ClienteDAOImp;
import main.java.integracion.factura.FacturaDAO;
import main.java.integracion.factura.FacturaDAOImp;
import main.java.integracion.producto.ProductoDAO;
import main.java.integracion.producto.ProductoDAOImp;

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
