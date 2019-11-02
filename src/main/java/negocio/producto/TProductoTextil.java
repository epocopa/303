package negocio.producto;

public class TProductoTextil extends TProducto {
	private String tejido;

	public TProductoTextil(int id, String nombre, int cantidad, double precio, String tejido, boolean activo) {
		super(id, nombre, cantidad, precio, false, activo);
		this.tejido = tejido;
	}

	public String getTejido() {
		return tejido;
	}

	public void setTejido(String tejido) {
		this.tejido = tejido;
	}

	@Override
	public String toString() {
		return "TProductoTextil{" +
				"tejido='" + tejido + '\'' +
				'}';
	}
}
