package negocio.empleado;

import negocio.turno.Turno;

import javax.persistence.*;
import java.util.List;

public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String DNI;
	private int salarioBase;
	private boolean activo;
	private String nombre;
	@Version
	private int version;
	@ManyToOne
	private Turno turno;
	@OneToMany(mappedBy = "empleado")
	private List<AsignacionGrupo> asignaciones;

}
