package negocio.empleado;

public class TTrabaja {
	private int idGrupo;
	private int idEmpleado;
	private int horas;

	public TTrabaja(int idGrupo, int idEmpleado, int horas) {
		this.idEmpleado = idEmpleado;
		this.idGrupo = idGrupo;
		this.horas = horas;
	}

	public TTrabaja() {
	}

	public int getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}
}