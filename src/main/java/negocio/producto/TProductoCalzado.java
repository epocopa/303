package negocio.producto;

public class TProductoCalzado extends TProducto {
	private int numero;

	public TProductoCalzado(int id, String nombre, int cantidad, double precio, int numero) {
		super(id, nombre, cantidad, precio, true);
		this.numero = numero;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "TProductoCalzado{" +
				"numero=" + numero +
				'}';
	}
}
