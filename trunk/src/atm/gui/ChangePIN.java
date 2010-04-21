package atm.gui;

import atm.gui.input.Keypad;
import atm.gui.screen.ChangePINScreen;
import atm.gui.screen.Screen;

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
		getScreen().show(Screen.CHANGE_PIN);
		
		//Enter a new PIN
		getScreen().getChangePINScreen().showMessage(ChangePINScreen.ENTER_PIN);
		int newPIN = getKeypad().readInput(Keypad.CHANGE_PIN_MODE);
		getScreen().getChangePINScreen().clearDisplay();
		
		if (newPIN == CANCELLED) {
			return;
		}
		
		//Confirm the PIN entered
		getScreen().getChangePINScreen().showMessage(ChangePINScreen.CONFIRM_PIN);
		int newPIN_confirm = getKeypad().readInput(Keypad.CHANGE_PIN_MODE);
		getScreen().getChangePINScreen().clearDisplay();
		
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
