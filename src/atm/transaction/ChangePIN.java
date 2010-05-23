package atm.transaction;

import atm.bank.BankDatabase;
import atm.input.Keypad;
import atm.screen.ChangePINScreen;
import atm.screen.ScreenType;
import atm.screen.Screen;

public class ChangePIN extends Transaction {
	
	private static final int CANCELLED = -1;
	
	/** ChangePIN constructor **/
	public ChangePIN(int userAccountNumber, Screen atmScreen, 
						BankDatabase atmBankDatabase, Keypad atmKeypad) {
		
		super(userAccountNumber, atmScreen, atmBankDatabase, atmKeypad);
	}

	/** performs the transaction **/
	public void execute()
	{
		getScreen().setType(ScreenType.CHANGE_PIN_TYPE);
		getScreen().clearDisplay();
		
		//Enter a new PIN
		getScreen().printMessage(ChangePINScreen.ENTER_PIN, 1);
		int newPIN = getKeypad().readInput(Keypad.CHANGE_PIN_MODE);		
		
		if (newPIN == CANCELLED) {
			return;
		}
		
		//Confirm the PIN entered
		getScreen().printMessage(ChangePINScreen.CONFIRM_PIN, 1);
		getScreen().clearDisplay();
		
		int newPIN_confirm = getKeypad().readInput(Keypad.CHANGE_PIN_MODE);	
		
		if (newPIN_confirm == CANCELLED) {
			return;
		}
		
		if (newPIN == 0)
			System.out.println("PIN must > 0");
		else if (newPIN != newPIN_confirm)
			System.out.println("2 PIN fields do not match..");
	    else {
	    	getBankDatabase().changePIN(getAccountNumber(), newPIN);
	    	System.out.println("PIN changed: " + getAccountNumber() + "/" + newPIN);
	    }	    	
	} // end execute()
}
