package negocio.empleado;

public class TDependiente extends TEmpleado{
	private boolean practicante;
	
	public TDependiente(int id, String nombre, String dni, double salarioBase, boolean activo, boolean practicante) {
		super(id, nombre, dni, salarioBase, activo, false);
		this.practicante = practicante;
	}
	
	public boolean getPracticante() {
		return practicante;
	}
	
	public void setPracticante(boolean practicante) {
		this.practicante = practicante;
	}
	
	@Override
	public String toString() {
		return "TDependiente{" +
				"practicante=" + practicante +
				'}';
	}
}