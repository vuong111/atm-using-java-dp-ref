package atm.transaction;

import atm.bank.BankDatabase;
import atm.input.Keypad;
import atm.screen.Screen;
import atm.screen.ScreenController;

public class Transfer extends Transaction {
	
	private static final int CANCELLED = -1;
	
	/** Transfer constructor **/
	public Transfer(int userAccountNumber, ScreenController atmScreen, 
						BankDatabase atmBankDatabase, Keypad atmKeypad) {
		
		super(userAccountNumber, atmScreen, atmBankDatabase, atmKeypad);
	}

	/** performs the transaction **/
	public void execute()
	{
		double availableBalance; // amount available for transfer
		
		getScreenController().showScreen(Screen.TRANSFER1);
		getScreenController().clearDisplay();
		
		int transferAccountNumber = getKeypad().readInput(Keypad.TRANSFER_MODE);		

		if (transferAccountNumber == CANCELLED) {
			return;
		}
		
		String transferAccountName = getBankDatabase().getFullName(transferAccountNumber);
		
		if (transferAccountName.equals("")) {
			System.out.println("Invalid account");
			return;
		}
		
		getScreenController().showScreen(Screen.TRANSFER2);
		getScreenController().clearDisplay();
		getScreenController().printMessage(transferAccountNumber + "", 1);
		getScreenController().printMessage(transferAccountName, 2);		
		
		double transferAmount = getKeypad().readInput(Keypad.TRANSFER_MODE);		
		
		if (transferAmount == CANCELLED) {
			return;
		}
		
		availableBalance = getBankDatabase().getAvailableBalance(getAccountNumber());

		if (transferAmount <= availableBalance) {
			getBankDatabase().transfer(getAccountNumber(), transferAccountNumber, transferAmount);
			System.out.println("Transfered " + transferAmount + " to account:" + transferAccountNumber);
		}
		else {
			System.out.println("not money enough for transfering..");
		}
	}
}
