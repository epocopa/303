package presentacion.factoriaCommand;

import presentacion.command.Command;

public abstract class FactoriaCommand {
	private static FactoriaCommand instance;
	
	public static FactoriaCommand getInstance() {
		if (instance == null)
			instance = new FactoriaCommandImpl();
		return instance;
	}

	public abstract Command getCommand(int evento);
}