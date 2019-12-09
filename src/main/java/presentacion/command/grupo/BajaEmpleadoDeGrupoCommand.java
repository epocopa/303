package presentacion.command.grupo;

import negocio.empleado.TTrabaja;
import negocio.factoriaSA.FactoriaSA;
import negocio.grupo.GrupoSA;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosGrupo;

public class BajaEmpleadoDeGrupoCommand implements Command {

	@Override
	public Context execute(Object datos) {
		String mensaje;
		TTrabaja trabaja = (TTrabaja) datos;
		GrupoSA grupoSA = FactoriaSA.getInstancia().generaGrupoSA();

		try {
			grupoSA.eliminarEmpleado(trabaja);
			mensaje = "El empleado se ha eliminado del grupo de trabajo con exito.";
			return new Context(EventosGrupo.BAJA_EMPLEADO_DE_GRUPO_OK, mensaje);
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new Context(EventosGrupo.BAJA_EMPLEADO_DE_GRUPO_KO, mensaje);
		}
	}
}