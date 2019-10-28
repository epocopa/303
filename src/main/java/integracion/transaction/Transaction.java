package main.java.integracion.transaction;

import java.sql.SQLException;

public interface Transaction {
	void commit() throws SQLException;
	void rollback() throws SQLException;
}
