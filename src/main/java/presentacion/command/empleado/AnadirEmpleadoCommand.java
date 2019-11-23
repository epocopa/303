package presentacion.command.empleado;

import negocio.empleado.EmpleadoSA;
import negocio.empleado.TEmpleado;
import negocio.factoriaSA.FactoriaSA;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosEmpleado;

public class AnadirEmpleadoCommand implements Command{

	@Override
	public Context execute(Object datos) {
		String mensaje;
		TEmpleado empleado = (TEmpleado) datos;
		EmpleadoSA empleadoSA = FactoriaSA.getInstancia().generaEmpleadoSA();
		
		try{
			empleadoSA.insertar(empleado);
			mensaje = "El empleado se ha registrado con exito. Su id es:" + empleado.getId();
			return new Context(EventosEmpleado.ANADIR_EMPLEADO_OK, mensaje);
		} catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosEmpleado.ANADIR_EMPLEADO_KO, mensaje);

		}
	}

}