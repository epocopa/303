package presentacion.command.empleado;

import negocio.empleado.EmpleadoSA;
import negocio.empleado.TEmpleado;
import negocio.factoriaSA.FactoriaSA;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosEmpleado;

public class ModificarEmpleadoCommand implements Command{

	@Override
	public Context execute(Object datos) {
		String mensaje;
		TEmpleado empleado = (TEmpleado) datos;
		EmpleadoSA empleadoSA = FactoriaSA.getInstancia().generaEmpleadoSA();
		
		try{
			empleadoSA.modificar(empleado);
			mensaje = "El empleado seleccionado ha sido editado correctamente.";
			return new Context(EventosEmpleado.MODIFICAR_EMPLEADO_OK, mensaje);

		} catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosEmpleado.MODIFICAR_EMPLEADO_KO, mensaje);
		}
	}
}