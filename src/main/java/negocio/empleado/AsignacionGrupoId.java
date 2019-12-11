package negocio.empleado;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AsignacionGrupoId implements Serializable {
	private int empleado;
	private int grupo;

	public AsignacionGrupoId(){}

	public int getEmpleado() {
		return empleado;
	}

	public void setEmpleado(int empleado) {
		this.empleado = empleado;
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
}
