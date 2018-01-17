
public class Executions implements Runnable {
	public Account account;
	public int localBalance = 0;

	public Executions(Account account) {
		this.account = account;
	}

	public void run() {
		Thread t = Thread.currentThread();
		for (int i = 0; i < 20; i++) { /** make 20 runs for each thread */
			if (Math.random() > 0.5) { /** 50% of the time */
				int withdrawAmount = (int) (Math.random() * 10); /** get a random amount of money */
				try {
					this.account.withdraw(withdrawAmount); /** withdraw that money */
					localBalance = localBalance - withdrawAmount;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else { /** the other 50% of the time */
				int depositAmount = (int) (Math.random() * 10); /** get an random amount */
				try {
					this.account.deposit(depositAmount); /** deposit that money */
					localBalance = localBalance + depositAmount;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Thread:" + t.getId() + "\nFinal Transaction:" + localBalance);
	}
}