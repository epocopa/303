package negocio.empleado;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Dependiente extends Empleado implements Serializable {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private int sumador;

	public Dependiente() {}

	public Dependiente(int sumador, TEmpleado t) {
		super(t);
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
