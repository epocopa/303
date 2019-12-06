package negocio.empleado;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dependiente extends Empleado {
	/*@Id no lo coge de empleado¿
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;*/
	@Column(nullable=false)
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
