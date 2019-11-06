package integracion.transactionManager;

import integracion.transaction.Transaction;
import integracion.transaction.TransactionImp;

import java.util.concurrent.ConcurrentHashMap;

//Transaktionsabwicklung
public class TransactionManagerImp extends TransactionManager {
	private ConcurrentHashMap<Thread, Transaction> map;

	public TransactionManagerImp() {
		this.map = new ConcurrentHashMap<>();
	}

	@Override
	public Transaction createTransaction() {
		Thread curent = Thread.currentThread();
		Transaction t = map.get(curent);

		if (t == null) {
			t = new TransactionImp();
			t.start();
			map.put(curent, t);
		}

		return t;
	}

	@Override
	public void removeTransaction() {
		map.remove(Thread.currentThread());
	}

	@Override
	public Transaction getTransaction() {
		return map.get(Thread.currentThread());
	}
}
