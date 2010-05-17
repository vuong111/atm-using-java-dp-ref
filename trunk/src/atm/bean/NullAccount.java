package atm.bean;

public class NullAccount extends Account {
	
	@Override
	public int getAccountNumber() {
		return 0;
	}
	
	@Override
	public String getFullName() {
		return "";
	}
	
	@Override
	public int getPIN() {
		return 0;
	}
	
	@Override
	public double getAvailableBalance() {
		return 0;
	}
	
	@Override
	public double getTotalBalance() {
		return 0;
	}
	
	@Override
	public boolean validatePIN(int userPIN) {
		return false;
	}
	
	@Override
	public void changePIN(int newPIN) {
		
	}
	
	@Override
	public void debit(double amount) {
		
	}
	
	@Override
	public void credit(double amount) {
		
	}
	
	@Override
	public void addTransaction(Transaction transaction) {
		
	}
	
	@Override
	public void removeTransaction(Transaction transaction) {
		
	}
}
