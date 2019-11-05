package negocio;

import java.time.LocalDate;

public class TFecha {
	private LocalDate fechaIni;
	private LocalDate fechaFin;

	public TFecha(LocalDate fechaIni, LocalDate fechaFin) {
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
	}

	public LocalDate getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(LocalDate fechaIni) {
		this.fechaIni = fechaIni;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
}
