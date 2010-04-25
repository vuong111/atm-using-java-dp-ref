package atm.bank;

import atm.bean.Account;
import atm.bean.Transaction;
import atm.dao.DAO;
import atm.dao.DAOFactory;
import atm.utils.ATMUtils;


public class BankDatabase {
	private DAO<Account> accountDAO;
	private DAO<Transaction> transactionDAO;
	
	public BankDatabase() {
		accountDAO = DAOFactory.getAccountDAO();
		transactionDAO = DAOFactory.getTransactionDAO();
	}
   	
	//============PRIVATE METHODS==============//
	
	/** retrieve Account object containing specified account number **/
	private Account getAccount(double accountNumber) {
		return accountDAO.find(accountNumber);
	}
	
	/** update Account **/
	private void updateAccount(Account userAccount) {
		accountDAO.update(userAccount);
	}
	
	/** insert Transaction **/
	private void insertTransaction(Transaction transaction) {
		transactionDAO.insert(transaction);
	}
	
	//=============PUBLIC METHODS==============//
	
	/** return current user full name **/
	public String getFullName(double userAccountNumber) {
		Account userAccount = getAccount(userAccountNumber);

		if (userAccount != null)
			return userAccount.getFullName();
		return null;
	}

	/** return available balance of Account with specified account number **/
	public double getAvailableBalance(double userAccountNumber) {
		return getAccount(userAccountNumber).getAvailableBalance();
	}

 	 /** return total balance of Account with specified account number **/
	public double getTotalBalance(double userAccountNumber) {
		return getAccount(userAccountNumber).getTotalBalance();
	}

	/**
	 *  determine whether user-specified account number and PIN match
	 *  those of an account in the database
	 **/
	public boolean authenticateUser(int userAccountNumber, int userPIN) {
		Account userAccount = getAccount(userAccountNumber);

		if (userAccount != null) {
			System.out.println(userAccount);
			return userAccount.validatePIN(userPIN);
		}
		
		return false;
	}
   
	/** change PIN code **/
	public void changePIN(int userAccountNumber, int newPIN) {
		Account userAccount = getAccount(userAccountNumber);
		userAccount.changePIN(newPIN);
		
		updateAccount(userAccount);
	}
   
	/** credit an amount to Account with specified account number **/
	public void credit(int userAccountNumber, double amount) {
		Account userAccount = getAccount(userAccountNumber);
		
		//update Account
		userAccount.credit(amount);		
		updateAccount(userAccount);
		
		//insert Transaction info
		Transaction transaction = 
			new Transaction(userAccount.getAccountNumber(), Transaction.DEPOSIT_TYPE, amount, 
							userAccount.getAvailableBalance(), ATMUtils.getCurrentDateTime());		
		insertTransaction(transaction);
	}

	/** debit an amount from of Account with specified account number **/
	public void debit(int userAccountNumber, double amount) {
		Account userAccount = getAccount(userAccountNumber);
		
		//update Account
		userAccount.debit(amount);
		updateAccount(userAccount);
		
		
		//insert Transaction info
		Transaction transaction = 
			new Transaction(userAccount.getAccountNumber(), Transaction.WITHDRAW_TYPE, amount, 
							userAccount.getAvailableBalance(), ATMUtils.getCurrentDateTime());		
		insertTransaction(transaction);
	}
	
	/** transfer an amount to specified account number **/
	public void transfer(int sourceAccountNumber, int destinationAccountNumber, double amount) {
		String currentDateTime = ATMUtils.getCurrentDateTime();
		
		Account sourceAccount = getAccount(sourceAccountNumber);
		Account destinationAccount = getAccount(destinationAccountNumber);
		
		//update source account
		sourceAccount.debit(amount);
		updateAccount(sourceAccount);
		
		//update destination Account
		destinationAccount.credit(amount);
		updateAccount(destinationAccount);
		
		//insert Transaction info into source account
		Transaction sTransaction = 
			new Transaction(sourceAccount.getAccountNumber(), destinationAccount.getAccountNumber(),
					Transaction.TRANSFER_TYPE, amount, sourceAccount.getAvailableBalance(), currentDateTime);		
		insertTransaction(sTransaction);

		//insert Transaction info into destination account
		Transaction dTransaction = 
			new Transaction(destinationAccount.getAccountNumber(), sourceAccount.getAccountNumber(),
					Transaction.TRANSFERED_TYPE, amount,	destinationAccount.getAvailableBalance(), currentDateTime);
		insertTransaction(dTransaction);
	}
}