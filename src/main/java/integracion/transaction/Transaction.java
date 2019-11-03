package integracion.transaction;

import java.sql.Connection;

public interface Transaction {
	void commit();
	void rollback();
	Connection getConnection();
}
