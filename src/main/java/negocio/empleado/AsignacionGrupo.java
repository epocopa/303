package negocio.empleado;

import negocio.grupo.Grupo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.time.LocalDate;

@Entity
public class AsignacionGrupo {
	@EmbeddedId
	private AsignacionGrupoId id;

	@ManyToOne
	@MapsId
	private Empleado empleado;

	@ManyToOne
	@MapsId
	private Grupo grupo;

	private LocalDate fecha;
}
