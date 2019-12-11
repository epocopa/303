package negocio.grupo;

import negocio.empleado.AsignacionGrupo;

import javax.persistence.*;

import java.util.List;

@Entity
@NamedQueries({
		@NamedQuery(name = "Grupo.READ", query = "SELECT g FROM Grupo g WHERE g.seccion = :seccion", lockMode = LockModeType.OPTIMISTIC),
		@NamedQuery(name = "Grupo.DELETEALL", query = "DELETE FROM Grupo"),
		@NamedQuery(name = "AsignacionGrupo.DELETEALL", query = "DELETE FROM AsignacionGrupo ")
})
public class Grupo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String seccion;
	
	@Column(nullable = false)
	private boolean activo;

	@Version
	private int version;
	
	@OneToMany(mappedBy = "grupo")
	private List<AsignacionGrupo> grupos;

	public Grupo() {
	}

	public Grupo(TGrupo g) {
		this.activo = g.isActivo();
		this.id = g.getId();
		this.seccion = g.getSeccion();
	}

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
