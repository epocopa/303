package integracion.transactionManager;

import integracion.transaction.Transaction;

public abstract class TransactionManager {
	private static TransactionManager instancia = null;

	public static TransactionManager getInstancia() {
		if (instancia == null) {
			instancia = new TransactionManagerImp();
		}
		return instancia;
	}

	public abstract Transaction createTransaction();
	public abstract void removeTransaction();
	public abstract Transaction getTransaction();
}
