package negocio.empleado;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Encargado extends Empleado {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private double multiplicador;

	public Encargado(double multiplicador) {
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
	public int getSalario() {
		return  (int) (getSalarioBase() * multiplicador);
	}
}
