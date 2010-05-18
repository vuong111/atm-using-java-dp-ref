package atm.bean;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Account 
{
	private int accountNumber;
	private String fullName;
	private int pin;
	private double availableBalance;
	
	private Set<TransactionVO> listTransaction = new HashSet<TransactionVO>();
	
	public Account() {
		
	}
	
	/** Account constructor initializes attributes **/
	public Account(int theAccountNumber, String theFullName, int thePIN,
		   			double theAvailableBalance) {
		accountNumber = theAccountNumber;
		fullName = theFullName;
		pin = thePIN;
		availableBalance = theAvailableBalance;
	}

	/** Introduce Null Object **/
	public static Account newNull() {
		return new NullAccount();
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
	
	/*****listTransaction*****/
	
	public Set<TransactionVO> getListTransaction() {
		return listTransaction;
	}
	
	public void setListTransaction(Set<TransactionVO> listTransaction) {
		this.listTransaction = listTransaction;
	}
	
	public void addTransaction(TransactionVO transaction) {
		if (!listTransaction.contains(transaction))
			listTransaction.add(transaction);
	}
	
	public void removeTransaction(TransactionVO transaction) {
		listTransaction.remove(transaction);
	}
	
	/*************
	 * BEHAVIORS *
	 *************/ 
	
	/** credits an amount to the account **/
	public void credit(double amount) {
		availableBalance += amount;
	}
   
	/** debits an amount from the account **/
	public void debit(double amount) {
		availableBalance -= amount;
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
		Iterator<TransactionVO> iterator = listTransaction.iterator();
		while (iterator.hasNext()) {
			TransactionVO transaction = (TransactionVO) iterator.next();
				msg += transaction.toString() + "\n";
		}
		return msg;
	}
}