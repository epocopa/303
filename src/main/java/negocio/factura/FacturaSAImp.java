package negocio.factura;

import java.util.List;

public class FacturaSAImp implements FacturaSA {

	@Override
	public boolean insertar(TFactura factura) throws Exception {
		return false;
	}

	@Override
	public TFactura mostrar(int id) throws Exception {
		return null;
	}

	@Override
	public List<TFactura> mostrarTodos() throws Exception {
		return null;
	}

	@Override
	public boolean modificar(TFactura factura) {
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		return false;
	}
}
