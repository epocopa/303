package integracion.transaction;

public interface Transaction {
	void commit();
	void rollback();
}
