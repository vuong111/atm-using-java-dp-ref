package atm.bean;

public class Transaction {
	public static final String WITHDRAW_TYPE = "WITHDRAW";
	public static final String DEPOSIT_TYPE = "DEPOSIT";
	public static final String CHANGE_PIN_TYPE = "CHANGE_PIN";
	public static final String TRANSFER_TYPE = "TRANSFER";
	public static final String TRANSFERED_TYPE = "TRANSFERED";
	
	private int accountNumber;
	private int referenceNumber;
	private String type;
	private double amount;
	private String dateTime;
	private double balance;
	
	public Transaction() {

	}
	
	public Transaction(int accNumber, int refNumber, String type, 
						double amount, double balance, String date) {
		
		this.accountNumber = accNumber;
		this.referenceNumber = refNumber;
		this.type = type;
		this.amount = amount;
		this.dateTime = date;
		this.balance = balance;		
	}
	
	/** Introduce Null Object **/
	public static Transaction newNull() {
		return new NullTransaction();
	}
	
	public Transaction(int accNumber, String type, 
						double amount, double balance, String date) {

		this(accNumber, 0, type, amount, balance, date);
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(int referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}	
	
	@Override
	public String toString() {
		return "accNumber: " + accountNumber + ", accRef: " + referenceNumber +
				", type: " + type + ", amount: " + amount + ", date: " + dateTime +
				", balance: " + balance;
	}
}
