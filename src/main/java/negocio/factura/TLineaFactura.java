package negocio.factura;

public class TLineaFactura {
	private int factura;
	private int producto;
	private int cantidad;

	public TLineaFactura(int factura, int producto, int cantidad) {
		this.factura = factura;
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public int getFactura() {
		return factura;
	}

	public void setFactura(int factura) {
		this.factura = factura;
	}

	public int getProducto() {
		return producto;
	}

	public void setProducto(int producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "TLineaFactura{" +
				"factura=" + factura +
				", producto=" + producto +
				", cantidad=" + cantidad +
				'}';
	}
}
