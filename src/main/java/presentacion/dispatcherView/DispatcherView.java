package main.java.presentacion.dispatcherView;

import main.java.presentacion.controladorAplicacion.Context;

public abstract class DispatcherView {
	private static DispatcherView instance;

	public static DispatcherView getInstance() {
		if (instance == null)
			instance = new DispatcherViewImpl();
		return instance;
	}

	public abstract void changeView(Context contexto);
}