package negocio.grupo;

import negocio.empleado.AsignacionGrupo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

public class Grupo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String seccion;
	@OneToMany(mappedBy = "empleado")
	private List<AsignacionGrupo> asignaciones;
}
