package negocio.empleado;

public class TEmpleado {
	private int id;
	private String nombre;
	private String dni;
	private int salarioBase;
	private boolean activo;
	private boolean encargado;
	private int idTurno;
	
	public TEmpleado(int id, String nombre, String dni, int salarioBase, boolean activo, boolean encargado, int idTurno){
		this.id = id;
		this.nombre = nombre;
		this.dni = dni;
		this.salarioBase = salarioBase;
		this.activo = activo;
		this.encargado = encargado;
		this.idTurno = idTurno;
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
	
	public int getSalarioBase() {
		return salarioBase;
	}
	
	public boolean isActivo() {
		return activo;
	}
	
	public boolean isEncargado() {
		return encargado;
	}
	
	public int getIdTurno() {
		return idTurno;
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
	
	public void setSalarioBase(int salarioBase) {
		this.salarioBase = salarioBase;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public void setEncargado(boolean encargado) {
		this.encargado = encargado;
	}
	
	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
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