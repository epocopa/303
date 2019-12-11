package negocio.empleado;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Dependiente extends Empleado implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int sumador;

	public Dependiente() {
	}

	public Dependiente(int sumador, TEmpleado t) {
		super(t);
		this.sumador = sumador;
	}

	public Dependiente(TDependiente e) {
		super(e);
		this.sumador = e.getSumador();
	}

	public int getSumador() {
		return sumador;
	}

	public void setSumador(int sumador) {
		this.sumador = sumador;
	}

	@Override
	public int calcularSalario() {
		return getSalarioBase() + sumador;
	}
}
