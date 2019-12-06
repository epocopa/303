package negocio.empleado;

import negocio.turno.Turno;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import java.util.List;

@Entity
@MappedSuperclass
@Inheritance(strategy=InheritanceType.) //InheritanceType.JOINED si tablas diferentes para subclases con solo datos de ellas
public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false,unique=true)
	private String DNI;
	@Column(nullable = false)
	private int salarioBase;
	@Column(nullable = false)
	private boolean activo;
	@Column(nullable = false)
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
