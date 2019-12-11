package negocio.empleado;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Encargado extends Empleado implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private double multiplicador;

	public Encargado() {
	}

	public Encargado(double multiplicador, TEmpleado t) {
		super(t);
		this.multiplicador = multiplicador;
	}

	public Encargado(TEncargado e) {
		super(e);
		this.multiplicador = e.getMultiplicador();
	}

	public double getMultiplicador() {
		return multiplicador;
	}

	public void setMultiplicador(double multiplicador) {
		this.multiplicador = multiplicador;
	}

	@Override
	public int calcularSalario() {
		return (int) (getSalarioBase() * multiplicador);
	}
}
