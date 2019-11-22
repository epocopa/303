package negocio.grupo;

public class TGrupo {
	
	private int id;
	private String seccion;
	private int horas;	// Es una cantidad de trabajo, ¿no?
	private boolean activo;
	
	public TGrupo(int id, String seccion, int horas, boolean activo) {
		this.id = id;
		this.seccion = seccion;
		this.horas = horas;
		this.activo = activo;
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

	public void setHoras(int horas) {
		this.horas = horas;
	}
	
	public int getHoras() {
		return horas;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	@Override
	public String toString() {
		return "TGrupo{" +
				"id=" + id +
				", secion=" + seccion +
				", horas=" + horas +
				", activo=" + activo +
				'}';
	}
}
