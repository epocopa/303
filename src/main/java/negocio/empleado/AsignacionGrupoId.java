package negocio.empleado;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AsignacionGrupoId implements Serializable {
	private int empleado;
	private int grupo;
}
