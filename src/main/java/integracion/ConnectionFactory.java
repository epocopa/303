package integracion;

import java.sql.Connection;

public abstract class ConnectionFactory {
	private static ConnectionFactory instancia = null;

	public static ConnectionFactory getInstancia() {
		if (instancia == null) {
			instancia = new ConnectionFactoryImp();
		}
		return instancia;
	}

	public abstract Connection getConnection();
}
