package negocio.empleado;

import negocio.turno.Turno;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({
		@NamedQuery(name = "Empleado.READBYDNI", query = "SELECT emp FROM Empleado emp WHERE emp.DNI = :dni", lockMode = LockModeType.OPTIMISTIC),
		@NamedQuery(name = "Empleado.READALL", query = "SELECT emp FROM Empleado emp"),
		@NamedQuery(name = "Empleado.DELETEALL", query = "DELETE FROM Empleado")
})
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Empleado implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String DNI;
	private int salarioBase;
	private boolean activo;
	private String nombre;
	private String dtype;
	@Version
	private int version;
	@ManyToOne
	private Turno turno;
	@OneToMany(mappedBy = "empleado")
	private List<AsignacionGrupo> asignaciones;

	public Empleado() {
	}

	public Empleado(TEmpleado e) {
		this.id = e.getId();
		this.DNI = e.getDNI();
		this.salarioBase = e.getSalarioBase();
		this.activo = e.isActivo();
		this.nombre = e.getNombre();
	}

	public abstract int calcularSalario();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String DNI) {
		this.DNI = DNI;
	}

	public int getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(int salarioBase) {
		this.salarioBase = salarioBase;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public List<AsignacionGrupo> getAsignaciones() {
		return asignaciones;
	}

	public void setAsignaciones(List<AsignacionGrupo> asignaciones) {
		this.asignaciones = asignaciones;
	}

	public String getType() {
		return dtype;
	}

	public void setType(String dtype) {
		this.dtype = dtype;
	}
}
