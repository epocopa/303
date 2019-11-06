package integracion.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransactionImp implements Transaction {
	private Connection conn;

	public TransactionImp() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trescerotres", "empleado", "password");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start() {
		try {
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void commit(){
		try {
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void rollback(){
		try {
			conn.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return conn;
	}
}
