package negocio.grupo;

import negocio.empleado.AsignacionGrupo;

import javax.persistence.*;
import java.util.List;

@Entity
public class Grupo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String seccion;
	private boolean activo;
	@OneToMany(mappedBy = "grupo")
	private List<AsignacionGrupo> grupos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public List<AsignacionGrupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<AsignacionGrupo> asignaciones) {
		this.grupos = asignaciones;
	}
}
