package negocio.empleado;

public class TDependiente extends TEmpleado{
	private int sumador;
	
	public TDependiente(int id, String nombre, String dni, int salarioBase, boolean activo, int sumador, int idTurno) {
		super(id, nombre, dni, salarioBase, activo, false, idTurno);
		this.sumador = sumador;
	}
	
	public int getSumador() {
		return sumador;
	}
	
	public void setSumador(int sumador) {
		this.sumador = sumador;
	}
	
	@Override
	public String toString() {
		return "TDependiente{" +
				"sumador=" + sumador +
				'}';
	}
}