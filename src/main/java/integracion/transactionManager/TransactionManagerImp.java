package integracion.transactionManager;

import integracion.transaction.Transaction;
import integracion.transaction.TransactionImp;

public class TransactionManagerImp extends TransactionManager {
	@Override
	public Transaction getTransaction() {
		return new TransactionImp();
	}
}
