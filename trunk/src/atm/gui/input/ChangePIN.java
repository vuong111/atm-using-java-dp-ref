package atm.gui.input;

import atm.gui.BankDatabase;
import atm.gui.Transaction;
import atm.gui.observer.Observable;
import atm.gui.observer.Observer;
import atm.gui.screen.Screen;

public class ChangePIN extends Transaction {
	/** ChangePIN constructor **/
	public ChangePIN(int userAccountNumber, Screen atmScreen, 
						BankDatabase atmBankDatabase, Keypad atmKeypad) {
		
		super(userAccountNumber, atmScreen, atmBankDatabase, atmKeypad);
	}

	/** performs the transaction **/
	public void execute()
	{
		Screen screen = getScreen();

		screen.show(Screen.CHANGE_PIN);
	   
		getKeypad().addObserver(new Observer() {
			@Override
			public void update(Observable observable) {
				if (getKeypad().getPressedKeyCode() == Keypad.RIGHT_KEY4) {
					exitTransaction();
				}
			}
		});
	}
}
