package negocio.cliente;

import java.time.LocalDate;

public class TCliente {
	private int id;
	private boolean activo;
	private LocalDate fecha_registro;
	private String nombre;

	public TCliente() {
	}

	public TCliente(int id, boolean activo, LocalDate fecha_registro,
			String nombre) {
		this.id = id;
		this.activo = activo;
		this.fecha_registro = fecha_registro;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public LocalDate getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(LocalDate fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "TCliente{" + "id=" + id + ", activo=" + activo
				+ ", fecha_registro=" + fecha_registro + ", nombre='" + nombre
				+ '\'' + '}';
	}
}
