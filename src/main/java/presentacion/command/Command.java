package presentacion.command;


import presentacion.controladorAplicacion.Context;

public interface Command {
	Context execute(Object datos);
}