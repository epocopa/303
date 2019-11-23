package presentacion.command.empleado;

import negocio.empleado.EmpleadoSA;
import negocio.empleado.TEmpleado;
import negocio.factoriaSA.FactoriaSA;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosEmpleado;

public class ModificarBuscarEmpleadoCommand implements Command{

	@Override
	public Context execute(Object datos) {
		String mensaje;
		int id = (int) datos;
		EmpleadoSA empleadoSA = FactoriaSA.getInstancia().generaEmpleadoSA();
		
		try{
			TEmpleado empleado = empleadoSA.mostrar(id);
			return new Context(EventosEmpleado.MODIFICAR_BUSCAR_EMPLEADO_OK, empleado);
		} catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosEmpleado.MODIFICAR_BUSCAR_EMPLEADO_KO, mensaje);
		}
	}
}