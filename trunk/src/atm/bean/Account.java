package atm.bean;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Account 
{
	private int accountNumber;
	private String fullName;
	private int pin;
	private double availableBalance; // funds available for withdrawal
	private double totalBalance; // funds available + pending deposits
	
	private Set<Transaction> listTransaction = new HashSet<Transaction>();
	
	/** Account constructor initializes attributes **/
	public Account( int theAccountNumber, String theFullName, int thePIN,
		   			double theAvailableBalance, double theTotalBalance ) {
		accountNumber = theAccountNumber;
		fullName = theFullName;
		pin = thePIN;
		availableBalance = theAvailableBalance;
		totalBalance = theTotalBalance;
	}

	/** returns account number **/
	public int getAccountNumber() {
      	return accountNumber;  
   	}   
   
	/** return user full name **/
	public String getFullName() {
	   	return fullName;
	}
   
	/** return account PIN **/
	public int getPIN() {
		return pin;
	}
	
	/** returns available balance **/
	public double getAvailableBalance() {
		return availableBalance;
	}

	/** returns the total balance **/
	public double getTotalBalance() {
		return totalBalance;
	}
	
	/*****listTransaction*****/
	
	public Set<Transaction> getListTransaction() {
		return listTransaction;
	}
	
	public void setListTransaction(Set<Transaction> listTransaction) {
		this.listTransaction = listTransaction;
	}
	
	public void addTransaction(Transaction transaction) {
		if (!listTransaction.contains(transaction))
			listTransaction.add(transaction);
	}
	
	public void removeTransaction(Transaction transaction) {
		listTransaction.remove(transaction);
	}
	
	/*************
	 * BEHAVIORS *
	 *************/ 
	
	/** credits an amount to the account **/
	public void credit(double amount) {
		availableBalance += amount;
		totalBalance += amount;
	}
   
	/** debits an amount from the account **/
	public void debit(double amount) {
		availableBalance -= amount;
		totalBalance -= amount;
	}

	/** determines whether a user-specified PIN matches PIN in Account **/
	public boolean validatePIN(int userPIN) {
		if (userPIN == pin)
			return true;
		else
			return false;
	}
   
	/** change PIN **/
	public void changePIN(int newPIN) {
		pin = newPIN;
	}
	
	@Override
	public String toString() {
		
		String msg = "AccNumber: " + accountNumber + ", name: " + fullName + 
					", balance: " + availableBalance + "\n-----------\n";
		Iterator<Transaction> iterator = listTransaction.iterator();
		while (iterator.hasNext()) {
			Transaction transaction = (Transaction) iterator.next();
				msg += transaction.toString() + "\n";
		}
		return msg;
	}
}