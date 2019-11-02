package negocio.factura;

import java.time.LocalDate;
import java.util.List;

public class TFactura {
	private int id;
	private double precio;
	private boolean activo;
	private LocalDate fecha;
	private List<TLineaFactura> lineaFacturas;
	private int cliente;

	public TFactura() {
	}
	
	public TFactura(int id, double precio, boolean activo, LocalDate fecha, List<TLineaFactura> lineaFacturas, int cliente) {
		this.id = id;
		this.precio = precio;
		this.activo = activo;
		this.fecha = fecha;
		this.lineaFacturas = lineaFacturas;
		this.cliente = cliente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public List<TLineaFactura> getLineaFacturas() {
		return lineaFacturas;
	}

	public void setLineaFacturas(List<TLineaFactura> lineaFacturas) {
		this.lineaFacturas = lineaFacturas;
	}

	public int getCliente() {
		return cliente;
	}

	public void setCliente(int cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "TFactura{" +
				"id=" + id +
				", precio=" + precio +
				", activo=" + activo +
				", fecha=" + fecha +
				", lineaFacturas=" + lineaFacturas +
				", cliente=" + cliente +
				'}';
	}
}