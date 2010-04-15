package atm.gui;

import java.util.Iterator;
import java.util.List;

import atm.gui.db.AccountDAO;

public class BankDatabase
{
	private Account accounts[];
   
	public BankDatabase()
	{
		initializeAccounts();
	}
  
	/** init accounts **/
	private void initializeAccounts() {
		AccountDAO accountDao = new AccountDAO();
		List<Account> accs = accountDao.getAccounts();
	   
		accounts = new Account[accs.size()];
		Iterator<Account> iterator = accs.iterator();
	   
		int i = 0;
		while (iterator.hasNext()) {
			Account account = (Account) iterator.next();
	   		accounts[i++] = account;
		}
	}
   
	/** retrieve Account object containing specified account number **/
	private Account getAccount(int accountNumber) {      
		for (Account currentAccount : accounts) {
			if (currentAccount.getAccountNumber() == accountNumber)
	   			return currentAccount;
		}

      	return null;
	}

	/**
	 *  determine whether user-specified account number and PIN match
	 *  those of an account in the database
	 **/
	public boolean authenticateUser(int userAccountNumber, int userPIN) {
		Account userAccount = getAccount(userAccountNumber);

		if (userAccount != null)
			return userAccount.validatePIN(userPIN);
		else
			return false;
	}
   
	/** change PIN code **/
	public void changePIN(int userAccountNumber, int newPIN) {
		Account userAccount = getAccount(userAccountNumber);
		
		if (userAccount != null)
			userAccount.changePIN(newPIN);
	}
   
	/** return available balance of Account with specified account number **/
	public double getAvailableBalance(int userAccountNumber) {
		return getAccount(userAccountNumber).getAvailableBalance();
	}

 	 /** return total balance of Account with specified account number **/
	public double getTotalBalance(int userAccountNumber) {
		return getAccount(userAccountNumber).getTotalBalance();
	}
   
	/** return current user full name **/
	public String getFullName(int userAccountNumber) {
		return getAccount(userAccountNumber).getFullName();
	}
   
	/** credit an amount to Account with specified account number **/
	public void credit(int userAccountNumber, double amount) {
		getAccount(userAccountNumber).credit(amount);
	}

	/** debit an amount from of Account with specified account number **/
	public void debit(int userAccountNumber, double amount) {
		getAccount(userAccountNumber).debit(amount);
	}
}
