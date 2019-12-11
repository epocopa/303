package negocio.grupo;

import java.util.ArrayList;
import java.util.List;

import negocio.empleado.TTrabaja;

public class TGrupo {
	
	private int id;
	private String seccion;
	private boolean activo;
	private int salario;
	private List<TTrabaja> empleadosGrupo;

	public TGrupo(int id, String seccion, boolean activo, int salario, List<TTrabaja> empleadosGrupo) {
		this.id = id;
		this.seccion = seccion;
		this.activo = activo;
		this.salario = salario;
		this.empleadosGrupo = empleadosGrupo;
	}
	
	public TGrupo(int id, String seccion, boolean activo, int salario) {
		this.id = id;
		this.seccion = seccion;
		this.activo = activo;
		this.salario = salario;
	}
	
	public TGrupo(int id, String seccion, boolean activo) {
		this.id = id;
		this.seccion = seccion;
		this.activo = activo;
		empleadosGrupo = new ArrayList<>();
	}
	
	public TGrupo() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSeccion() {
		return seccion;
	}
	
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public int getSalario() {
		return salario;
	}
	
	public void setSalario(int salario) {
		this.salario = salario;
	}
	
	public List<TTrabaja> getListaEmpleados() {
		return empleadosGrupo;
	}
	
	public void setListaEmpleados(List<TTrabaja> empleadosGrupo) {
		this.empleadosGrupo = empleadosGrupo;
	}
	
	@Override
	public String toString() {
		return "TGrupo{" +
				"id=" + id +
				", secion=" + seccion +
				", activo=" + activo +
				'}';
	}
}