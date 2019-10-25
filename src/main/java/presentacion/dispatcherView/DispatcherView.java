package presentacion.dispatcherView;

import presentacion.controladorAplicacion.Context;
import presentacion.dispatcherView.DispatcherViewImpl;

public abstract class DispatcherView {
	private static DispatcherView instance;

	public static DispatcherView getInstance() {
		if (instance == null)
			instance = new DispatcherViewImpl();
		return instance;
	}

	public abstract void changeView(Context contexto);
}