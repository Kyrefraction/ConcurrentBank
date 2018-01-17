public class Account {
	int balance;

	public Account(int balance) {
		this.balance = balance;
	}

	public int getBalance() {
		return balance;
	}

	public synchronized void withdraw(int amount) throws InterruptedException {
        Thread t = Thread.currentThread(); /** get the current thread and put it into t */
		while (amount > balance) { /** while amount is greater than balance, this should never happen - the person is out of funds */
			try {
				System.out.println("insufficient funds");
				wait(); /** tell the currently executing thread to wait */
			} catch (InterruptedException e) {
				System.out.println("Thread Blocked"); /** or block it */
				e.printStackTrace();
			}
		} /** when balance is greater than amount */

		balance = balance - amount; /** critical, take out the money */
		if(t.getId() < 10) {
			System.out.println(t.getId() + "              " + amount + "             -------    " + balance); /** print out transaction info */
		} else {
			System.out.println(t.getId() + "             " + amount + "             -------    " + balance);

		}
	}

	public synchronized void deposit(int amount) throws InterruptedException {
        Thread t = Thread.currentThread(); /** get the current thread and put it into t */
		balance = balance + amount; /** critical, put in the money */
		if(t.getId() < 10) {
			System.out.println(t.getId() + "              ----------    " + amount + "          " + balance); /** print out transaction info */
		} else {
			System.out.println(t.getId() + "             ----------    " + amount + "          " + balance);
		}
		notify(); /** wake up the thread waiting on the monitor */
	}
}
