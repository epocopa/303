package negocio.factura;

import negocio.producto.TProducto;

public class TOAProductoFactura {
	private TLineaFactura linea;
	private TProducto producto;

	public TOAProductoFactura(TLineaFactura linea, TProducto producto) {
		this.linea = linea;
		this.producto = producto;
	}

	public TLineaFactura getLinea() {
		return linea;
	}

	public void setLinea(TLineaFactura linea) {
		this.linea = linea;
	}

	public TProducto getProducto() {
		return producto;
	}

	public void setProducto(TProducto producto) {
		this.producto = producto;
	}
}
