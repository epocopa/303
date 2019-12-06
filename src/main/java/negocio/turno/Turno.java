package negocio.turno;

import negocio.empleado.Empleado;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Turno {//TODO mapear empleado tambien
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private boolean activo;
	private LocalTime inicio;
	private LocalTime fin;
	@OneToMany(mappedBy = "turno")
	private List<Empleado> empleados;

<<<<<<< HEAD
	public Turno(){}

	public Turno(TTurno t){
		this.activo = t.isActivo();
		this.nombre = t.getNombre();
		this.id = t.getId();
		this.inicio = t.getHoraInicio();
		this.fin = t.getHoraFin();
	}

=======
	public Turno(int id, String nombre, boolean activo, int inicio, int fin){
		this.id = id;
		this.nombre = nombre;
		this.activo = activo;
		this.inicio = inicio;
		this.fin = fin;	
	}	
>>>>>>> c47a4600c97294d0e2c04e6e30fb159e2c21ce37
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
