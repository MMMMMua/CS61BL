/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {

	private int balance;
	private Account parentAccount;
	/** Initialize an account with the given BALANCE. */
	public Account(int balance) {
		this.balance = balance;
		this.parentAccount = null;
	}

	/** Return the number of dollars in the account. */
	public int getBalance() {
		return this.balance;
	}

	public void setParent(Account parent) {
		parentAccount = parent;
	}
	/** Deposits AMOUNT into the current account. */
	public void deposit(int amount) {
		if (amount < 0) {
			System.out.println("Cannot deposit negative amount.");
		} else {
			this.balance = this.balance + amount;
		}
	}

	/** Subtract AMOUNT from the account if possible. If subtracting AMOUNT
	 *	would leave a negative balance, print an error message and leave the
	 *	balance unchanged.
	 */
	public boolean withdraw(int amount) {
		if (amount < 0) {
			System.out.println("Cannot withdraw negative amount.");
		} else if (this.balance < amount) {
			if (this.parentAccount == null)
				System.out.println("Insufficient funds");
			else
				if (parentAccount.withdraw(amount - this.balance) == true)
					return withdraw(this.balance);
					
		} else {
			this.balance = this.balance - amount;
			return true;
		}
		return false;
	}

	/** Merge account OTHER into this account by removing all money from OTHER
	 *	and depositing it into this account.
     */
    public void merge(Account other) {
        // TODO Put your own code here
		this.deposit(other.getBalance());
		other.withdraw(other.getBalance());
    }

	public static void main(String[] args) {
		Account kathy = new Account(500);
		Account megan = new Account(100);
		megan.setParent(kathy);
		int g = Integer.parseInt(args[0]);
		System.out.println(megan.withdraw(g));
		System.out.println(megan.getBalance());
		System.out.println(kathy.getBalance());
	}
}
