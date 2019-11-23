package negocio.empleado;

public class TEmpleado {
	private int id;
	private String nombre;
	private String dni;
	private double salarioBase;
	private boolean activo;
	private boolean encargado;
	
	public TEmpleado(int id, String nombre, String dni, double salarioBase, boolean activo, boolean encargado){
		this.id = id;
		this.nombre = nombre;
		this.dni = dni;
		this.salarioBase = salarioBase;
		this.activo = activo;
		this.encargado = encargado;
	}
	
	public TEmpleado(){}
	
	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDNI() {
		return dni;
	}
	
	public double getSalarioBase() {
		return salarioBase;
	}
	
	public boolean isActivo() {
		return activo;
	}
	
	public boolean isEncargado() {
		return encargado;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setDNI(String dni) {
		this.dni = dni;
	}
	
	public void setSalarioBase(double salarioBase) {
		this.salarioBase = salarioBase;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public void setEncargado(boolean encargado) {
		this.encargado = encargado;
	}

	@Override
	public String toString() {
		return "TEmpleado{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				", dni=" + dni +
				", salarioBase=" + salarioBase +
				", activo=" + activo +
				'}';
	}
}