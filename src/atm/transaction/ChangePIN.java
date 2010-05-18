package atm.transaction;

import atm.bank.BankDatabase;
import atm.input.Keypad;
import atm.screen.ChangePINScreen;
import atm.screen.Screen;
import atm.screen.ScreenController;

public class ChangePIN extends Transaction {
	
	private static final int CANCELLED = -1;
	
	/** ChangePIN constructor **/
	public ChangePIN(int userAccountNumber, ScreenController atmScreen, 
						BankDatabase atmBankDatabase, Keypad atmKeypad) {
		
		super(userAccountNumber, atmScreen, atmBankDatabase, atmKeypad);
	}

	/** performs the transaction **/
	public void execute()
	{
		getScreenController().showScreen(Screen.CHANGE_PIN);
		getScreenController().clearDisplay();
		
		//Enter a new PIN
		getScreenController().printMessage(ChangePINScreen.ENTER_PIN, 1);
		int newPIN = getKeypad().readInput(Keypad.CHANGE_PIN_MODE);
		
		
		if (newPIN == CANCELLED) {
			return;
		}
		
		//Confirm the PIN entered
		getScreenController().printMessage(ChangePINScreen.CONFIRM_PIN, 1);
		getScreenController().clearDisplay();
		
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
	    	System.out.println("PIN changed");
	    }	    	
	} // end execute()
}
