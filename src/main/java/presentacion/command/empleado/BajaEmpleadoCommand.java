package presentacion.command.empleado;

import negocio.empleado.EmpleadoSA;
import negocio.factoriaSA.FactoriaSA;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosEmpleado;

public class BajaEmpleadoCommand implements Command{

	@Override
	public Context execute(Object datos) {
		String mensaje;
		int id = (int) datos;
		EmpleadoSA empleadoSA = FactoriaSA.getInstancia().generaEmpleadoSA();
		
		try{
			empleadoSA.eliminar(id);
			mensaje = "El empleado se ha eliminado con exito.";
			return new Context(EventosEmpleado.BAJA_EMPLEADO_OK, mensaje);
		}catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosEmpleado.BAJA_EMPLEADO_KO, mensaje);
		}
	}
}