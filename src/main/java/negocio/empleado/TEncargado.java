package negocio.empleado;

public class TEncargado extends TEmpleado{
	private double multiplicador;
	
	public TEncargado(int id, String nombre, String dni, int salarioBase, boolean activo, double multiplicador, int idTurno) {
		super(id, nombre, dni, salarioBase, activo, true, idTurno);
		this.multiplicador = multiplicador;
	}
	
	public double getMultiplicador() {
		return multiplicador;
	}
	
	public void setMultiplicador(double multiplicador) {
		this.multiplicador = multiplicador;
	}
	
	@Override
	public String toString() {
		return "TEncargado{" +
				"multiplicador=" + multiplicador +
				'}';
	}
}