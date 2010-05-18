package atm.bank;

import atm.bean.Account;
import atm.bean.TransactionVO;
import atm.dao.DAO;
import atm.dao.DAOFactory;
import atm.utils.ATMUtils;


public class BankDatabase {
	private DAO<Account> accountDAO;
	private DAO<TransactionVO> transactionDAO;
	
	public BankDatabase() {
		accountDAO = DAOFactory.getAccountDAO();
		transactionDAO = DAOFactory.getTransactionDAO();
	}

	/** retrieve Account object containing specified account number **/
	private Account getAccount(double accountNumber) {
		return accountDAO.find(accountNumber);
	}
	
	/** update Account **/
	private void updateAccount(Account userAccount) {
		accountDAO.update(userAccount);
	}
	
	/** insert Transaction **/
	private void insertTransaction(TransactionVO transaction) {
		transactionDAO.insert(transaction);
	}
	
	/** debit, then return available balance **/
	private double getBalanceAfterDebit(int userAccountNumber, double amount) {
		Account userAccount = getAccount(userAccountNumber);
		userAccount.debit(amount);
		updateAccount(userAccount);
		
		return userAccount.getAvailableBalance();
	}
	
	/** credit, then return available balance **/
	private double getBalanceAfterCredit(int userAccountNumber, double amount) {
		Account userAccount = getAccount(userAccountNumber);
		userAccount.credit(amount);
		updateAccount(userAccount);
		
		return userAccount.getAvailableBalance();
	}
	
	/*--------------------------*/
	
	/** return current user full name **/
	public String getFullName(double userAccountNumber) {
		Account userAccount = getAccount(userAccountNumber);
		return userAccount.getFullName(); // Introduce Null Object 
	}

	/** return available balance of Account with specified account number **/
	public double getAvailableBalance(double userAccountNumber) {
		return getAccount(userAccountNumber).getAvailableBalance();
	}

	/**
	 *  determine whether user-specified account number and PIN match
	 *  those of an account in the database
	 **/
	public boolean authenticateUser(int userAccountNumber, int userPIN) {
		return getAccount(userAccountNumber).validatePIN(userPIN);
	}
   
	/** change PIN code **/
	public void changePIN(int userAccountNumber, int newPIN) {		
		Account userAccount = getAccount(userAccountNumber);
		userAccount.changePIN(newPIN);
		
		updateAccount(userAccount);
	}
   
	/** deposit -- credit an amount to Account with specified account number **/
	public void credit(int userAccountNumber, double amount) {
		double availableBalance = getBalanceAfterCredit(userAccountNumber, amount);
		
		//insert Transaction info
		TransactionVO transaction = 
			new TransactionVO(userAccountNumber, TransactionVO.DEPOSIT_TYPE, amount, 
					availableBalance, ATMUtils.getCurrentDateTime());		
		insertTransaction(transaction);
	}

	/** withdraw -- debit an amount from of Account with specified account number **/
	public void debit(int userAccountNumber, double amount) {
		double availableBalance = getBalanceAfterDebit(userAccountNumber, amount);
		
		//insert Transaction info
		TransactionVO transaction = 
			new TransactionVO(userAccountNumber, TransactionVO.WITHDRAW_TYPE, amount, 
					availableBalance, ATMUtils.getCurrentDateTime());
		insertTransaction(transaction);
	}
	
	/** transfer -- transfer an amount to specified account number **/
	public void transfer(int sourceAccountNumber, int destinationAccountNumber, double amount) {
		String currentDateTime = ATMUtils.getCurrentDateTime();
		
		//insert Transaction info into source account
		double srcAvailableBalance = getBalanceAfterDebit(sourceAccountNumber, amount);		
		TransactionVO srcTransaction = 
			new TransactionVO(sourceAccountNumber, destinationAccountNumber,
					TransactionVO.TRANSFER_TYPE, amount, srcAvailableBalance, currentDateTime);		
		insertTransaction(srcTransaction);
		
		//insert Transaction info into destination account
		double destAvailableBalance = getBalanceAfterCredit(destinationAccountNumber, amount);
		TransactionVO destTransaction = 
			new TransactionVO(destinationAccountNumber, sourceAccountNumber,
					TransactionVO.TRANSFERED_TYPE, amount, destAvailableBalance, currentDateTime);
		insertTransaction(destTransaction);
	}
}