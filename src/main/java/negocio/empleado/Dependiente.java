package negocio.empleado;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Dependiente extends Empleado {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private int sumador;

	public Dependiente(int sumador) {
		this.sumador = sumador;
	}

	public Dependiente(TDependiente e) {
		super(e);
		this.sumador = e.getSumador();
	}

	@Override
	public int getSalario() {
		return getSalarioBase() + sumador;
	}
}
