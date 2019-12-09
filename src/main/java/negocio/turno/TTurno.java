package negocio.turno;

import java.time.LocalTime;

public class TTurno {

	private int id;
	private String nombre;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private boolean activo;

	public TTurno(int id, String nombre, LocalTime horaInicio,
			LocalTime horaFin, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.activo = activo;
	}

	public TTurno() {
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

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "TTurno{" + "id=" + id + ", nombre=" + nombre + ", horaInicio="
				+ horaInicio + ", horaFin=" + horaFin + ", activo =" + activo
				+ '}';
	}
}