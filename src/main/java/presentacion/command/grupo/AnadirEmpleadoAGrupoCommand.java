package presentacion.command.grupo;

import negocio.empleado.TTrabaja;
import negocio.factoriaSA.FactoriaSA;
import negocio.grupo.GrupoSA;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosGrupo;

public class AnadirEmpleadoAGrupoCommand  implements Command {

	@Override
	public Context execute(Object datos) {
		String mensaje;
		TTrabaja trabaja = (TTrabaja) datos;
		GrupoSA grupoSA = FactoriaSA.getInstancia().generaGrupoSA();
		
		try{
			grupoSA.insertarEmpleado(trabaja);
			mensaje = "El empleado se ha añadido al grupo de trabajo con exito.";
			return new Context(EventosGrupo.ANADIR_EMPLEADO_A_GRUPO_OK, mensaje);
		} catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosGrupo.ANADIR_EMPLEADO_A_GRUPO_KO, mensaje);
		}
	}
}