package main.java.presentacion.command;

import main.java.presentacion.controladorAplicacion.Context;

public interface Command {
	Context execute(Object datos);
}