package atm.transaction;

import atm.bank.BankDatabase;
import atm.input.Keypad;
import atm.screen.ScreenType;
import atm.screen.Screen;

public class Transfer extends Transaction {
	
	private static final int CANCELLED = -1;
	
	private int transferAccountNumber;
	private double transferAmount;
	
	/** Transfer constructor **/
	public Transfer(int userAccountNumber, Screen atmScreen, 
						BankDatabase atmBankDatabase, Keypad atmKeypad) {
		
		super(userAccountNumber, atmScreen, atmBankDatabase, atmKeypad);
	}

	/** performs the transaction **/
	public void execute()
	{
		//nhập số tài khoản muốn chuyển tiền
		transferAccountNumber = inputTransferAccountNumber();		

		if (!transferAccountIsExisting())
			return;
		
		//nhập số tiền muốn chuyển
		transferAmount = inputTransferAmount();	
		
		if (!transferAmountIsAvailable())
			return;
		
		//thực hiện chuyển khoản
		performTransfer();
	}

	private boolean transferAccountIsExisting() {		
		if (transferAccountNumber == CANCELLED) {
			System.out.println("Transfer cancelled..");
			return false;
		}
		
		String transferAccountName = getBankDatabase().getFullName(transferAccountNumber);
		if (transferAccountName.equals("")) {
			System.out.println("invalid account");
			return false;
		}
		
		return true;
	}
	
	private boolean transferAmountIsAvailable() {
		if (transferAmount == CANCELLED) {
			System.out.println("Transfer cancelled..");
			return false;
		}
		
		double availableBalance = getBankDatabase().getAvailableBalance(getAccountNumber());
		if (transferAmount > availableBalance) {
			System.out.println("not money enough for transfering..");
			return false;
		}
		
		return true;		
	}
	
	private int inputTransferAccountNumber() {
		getScreen().setType(ScreenType.TRANSFER1_TYPE);
		getScreen().clearDisplay();
		
		return getKeypad().readInput(Keypad.TRANSFER_MODE);
	}
	
	private double inputTransferAmount() {
		String transferAccountName = getBankDatabase().getFullName(transferAccountNumber);
		
		getScreen().setType(ScreenType.TRANSFER2_TYPE);
		getScreen().clearDisplay();
		getScreen().printMessage(transferAccountNumber + "", 1);
		getScreen().printMessage(transferAccountName, 2);
		
		return getKeypad().readInput(Keypad.TRANSFER_MODE);
	}
	
	private void performTransfer() {
		getBankDatabase().transfer(getAccountNumber(), transferAccountNumber, transferAmount);
		System.out.println("Transfered " + transferAmount + " to account:" + transferAccountNumber);	
	}
}
