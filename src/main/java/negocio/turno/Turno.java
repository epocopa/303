package negocio.turno;

import negocio.empleado.Empleado;

import javax.persistence.*;
import java.util.List;

@Entity
public class Turno {//TODO mapear empleado tambien
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private boolean activo;
	private int inicio;
	private int fin;
	@OneToMany(mappedBy = "turno")
	private List<Empleado> empleados;

	public Turno(int id, String nombre, boolean activo, int inicio, int fin){
		this.id = id;
		this.nombre = nombre;
		this.activo = activo;
		this.inicio = inicio;
		this.fin = fin;	
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

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public int getFin() {
		return fin;
	}

	public void setFin(int fin) {
		this.fin = fin;
	}

	public void setEmpleados(List<Empleado> empleados){
		this.empleados = empleados;
	}

	public List<Empleado> getEmpleados(){
		return this.empleados;
	}
}
