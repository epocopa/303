package negocio.empleado;

import negocio.grupo.Grupo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.time.LocalDate;

@Entity
public class AsignacionGrupo {
	@EmbeddedId
	private AsignacionGrupoId id;

	@ManyToOne
	@MapsId
	private Empleado empleado;

	@ManyToOne
	@MapsId
	private Grupo grupo;

	private LocalDate fecha;

	public AsignacionGrupo(){}
	
	public AsignacionGrupoId getId() {
		return id;
	}

	public void setId(AsignacionGrupoId id) {
		this.id = id;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	
}
