package main.java.integracion.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransactionImp implements Transaction {
	private Connection conn;

	public TransactionImp() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/303", "empleado", "password");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void commit() throws SQLException {
		conn.commit();
	}

	@Override
	public void rollback() throws SQLException {
		conn.rollback();
	}
}
