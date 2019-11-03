package negocio.producto;

import java.util.List;

public class ProductoSAImp implements ProductoSA {
	@Override
	public boolean insertar(TProducto producto) throws Exception {
		return false;
	}

	@Override
	public TProducto mostrar(int id) throws Exception {
		return null;
	}

	@Override
	public List<TProducto> mostrarTodos() throws Exception {
		return null;
	}

	@Override
	public boolean modificar(TProducto producto) {
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		return false;
	}
}
