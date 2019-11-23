package negocio.empleado;

public class TEncargado extends TEmpleado{
	private double incentivo;
	
	public TEncargado(int id, String nombre, String dni, double salarioBase, boolean activo, double incentivo) {
		super(id, nombre, dni, salarioBase, activo, true);
		this.incentivo = incentivo;
	}
	
	public double getIncentivo() {
		return incentivo;
	}
	
	public void setPracticante(double incentivo) {
		this.incentivo = incentivo;
	}
	
	@Override
	public String toString() {
		return "TEncargado{" +
				"incentivo=" + incentivo +
				'}';
	}
}