package main.java.integracion.transactionManager;

import main.java.integracion.transaction.Transaction;
import main.java.integracion.transaction.TransactionImp;

public class TransactionManagerImp extends TransactionManager {
	//Transaktionsabwicklung
	@Override
	public Transaction getTransaction() {
		return new TransactionImp();
	}
}
