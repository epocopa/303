package negocio.turno;

import negocio.empleado.Empleado;

import javax.persistence.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@NamedQueries({
		@NamedQuery(name = "Turno.READ", query = "SELECT trn FROM Turno trn WHERE trn.nombre = :nombre", lockMode = LockModeType.OPTIMISTIC),
		@NamedQuery(name = "Turno.DELETEALL", query = "DELETE FROM Turno")
})
public class Turno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private String nombre;
	
	@Column(nullable=false)
	private boolean activo;
	
	@Column(nullable=false)
	private LocalTime inicio;
	
	@Column(nullable=false)
	private LocalTime fin;

	@Version
	private int version;
	
	@OneToMany(mappedBy = "turno")
	private List<Empleado> empleados;

	public Turno(){}

	public Turno(TTurno t){
		this.activo = t.isActivo();
		this.nombre = t.getNombre();
		this.id = t.getId();
		this.inicio = t.getHoraInicio();
		this.fin = t.getHoraFin();
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

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public LocalTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalTime inicio) {
		this.inicio = inicio;
	}

	public LocalTime getFin() {
		return fin;
	}

	public void setFin(LocalTime fin) {
		this.fin = fin;
	}

	public void setEmpleados(List<Empleado> empleados){
		this.empleados = empleados;
	}

	public List<Empleado> getEmpleados(){
		return this.empleados;
	}
}
