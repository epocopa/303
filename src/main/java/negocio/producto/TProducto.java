package negocio.producto;

public class TProducto {
	private int id;
	private String nombre;
	private int cantidad;
	private double precio;
	private boolean calzado;
	private boolean activo;

	public TProducto(int id, String nombre, int cantidad, double precio, boolean calzado, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
		this.calzado = calzado;
		this.activo = activo;
	}

	public TProducto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public boolean isCalzado() {
		return calzado;
	}

	public void setCalzado(boolean calzado) {
		this.calzado = calzado;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "TProducto{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				", cantidad=" + cantidad +
				", precio=" + precio +
				", calzado=" + calzado +
				", activo=" + activo +
				'}';
	}
}

