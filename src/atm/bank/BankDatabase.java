package atm.bank;


public class BankDatabase {
	private AccountDAO accountDAO;
	
	public BankDatabase() {
		accountDAO = new AccountDAO(); //will be changed to using DAOFactory soon..
	}
   	
	//============PRIVATE METHODS==============//
	
	/** retrieve Account object containing specified account number **/
	private Account getAccount(int accountNumber) {
		return accountDAO.getAccount(accountNumber);
	}
	
	/** update Account **/
	private void updateAccount(Account userAccount) {
		accountDAO.updateAccount(userAccount);
	}
	
	//=============PUBLIC METHODS==============//
	
	/** return current user full name **/
	public String getFullName(int userAccountNumber) {
		Account userAccount = getAccount(userAccountNumber);

		if (userAccount != null)
			return userAccount.getFullName();
		return null;
	}

	/** return available balance of Account with specified account number **/
	public double getAvailableBalance(int userAccountNumber) {
		return getAccount(userAccountNumber).getAvailableBalance();
	}

 	 /** return total balance of Account with specified account number **/
	public double getTotalBalance(int userAccountNumber) {
		return getAccount(userAccountNumber).getTotalBalance();
	}

	/**
	 *  determine whether user-specified account number and PIN match
	 *  those of an account in the database
	 **/
	public boolean authenticateUser(int userAccountNumber, int userPIN) {
		Account userAccount = getAccount(userAccountNumber);

		if (userAccount != null)
			return userAccount.validatePIN(userPIN);
		
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
		userAccount.credit(amount);
		
		updateAccount(userAccount);
	}

	/** debit an amount from of Account with specified account number **/
	public void debit(int userAccountNumber, double amount) {
		Account userAccount = getAccount(userAccountNumber);
		userAccount.debit(amount);
		
		updateAccount(userAccount);
	}
	
	/** transfer an amount to specified account number **/
	public void transfer(int sourceAccountNumber, int destinationAccountNumber, double amount) {
		debit(sourceAccountNumber, amount);
		credit(destinationAccountNumber, amount);
	}
}