package negocio.empleado;

import negocio.turno.Turno;

import javax.persistence.*;
import java.util.List;
@Entity
public abstract class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String DNI;
	private int salarioBase;
	private boolean activo;
	private String nombre;
	@Version
	private int version;
	@ManyToOne
	private Turno turno;
	@OneToMany(mappedBy = "empleado")
	private List<AsignacionGrupo> asignaciones;

	public Empleado() {}

	public Empleado(TEmpleado e){
		this.id = e.getId();
		this.DNI = e.getDNI();
		this.salarioBase = e.getSalarioBase();
		this.activo = e.isActivo();
		this.nombre = e.getNombre();
		//TODO turno y grupo?
	}

	public abstract int getSalario();

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
}
