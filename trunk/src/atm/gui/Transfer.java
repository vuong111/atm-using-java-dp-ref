package atm.gui;

import atm.gui.input.Keypad;
import atm.gui.observer.Observable;
import atm.gui.observer.Observer;
import atm.gui.screen.Screen;

public class Transfer extends Transaction {
	/** Transfer constructor **/
	public Transfer(int userAccountNumber, Screen atmScreen, 
						BankDatabase atmBankDatabase, Keypad atmKeypad) {
		
		super(userAccountNumber, atmScreen, atmBankDatabase, atmKeypad);
	}

	/** performs the transaction **/
	public void execute()
	{
		Screen screen = getScreen();

		screen.show(Screen.TRANSFER);
	   
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
