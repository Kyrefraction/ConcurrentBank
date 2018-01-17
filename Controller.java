
public class Controller {
	/** Author: Vincenzo Scialpi */

	public static void main(String[] args) throws InterruptedException {
		int a = 0; /** Variable for the amount of threads to run */
		int balance = 1000; /** Variable for initial balance of the account */
		Account testAccount = new Account(balance); /** create the account to withdraw from and deposit in */
		if (args.length > 0) {
		    try {
		        a = Integer.parseInt(args[0]); /** take the first command line argument and put it into 'a' */
				balance = Integer.parseInt(args[1]); /** take the second command line argument and put it into 'balance' */
				testAccount.balance = balance; /** make the accounts balance the balance that was entered */
				Thread[] threadArray = new Thread[a]; /** make an array of threads of size 'a' */
				System.out.print("Transaction    ");
				System.out.print("Withdrawal    ");
				System.out.print("Deposit    ");
				System.out.println("Balance    "); /** make the column headers */
				for(int i = 0; i < a; i++) { /** make an 'a' amount of threads */
					threadArray[i] = new Thread(new Executions(testAccount)); /** create each thread */
				    threadArray[i].start(); /** start the threads */
				}
				Thread.sleep(1000);
		    } catch (NumberFormatException e) {
		        System.err.println("Argument " + args[0] + " must be an integer."); /** if the user inputs something that isn't a number close the program */
		        System.exit(1);
		    }
		}
		
	}
}
