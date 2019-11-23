package presentacion.command.empleado;

import java.util.List;

import negocio.empleado.EmpleadoSA;
import negocio.empleado.TEmpleado;
import negocio.factoriaSA.FactoriaSA;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosEmpleado;

public class ListarEmpleadosCommand implements Command{

	@Override
	public Context execute(Object datos) {
		String mensaje;
		EmpleadoSA empleadoSA = FactoriaSA.getInstancia().generaEmpleadoSA();
		
		try{
			List<TEmpleado> listaEmpleados = empleadoSA.mostrarTodos();
			return new Context(EventosEmpleado.LISTAR_EMPLEADO_OK, listaEmpleados);
		}catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosEmpleado.LISTAR_EMPLEADO_KO, mensaje);
		}
	}
}