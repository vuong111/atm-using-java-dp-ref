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
		getScreen().show(Screen.TRANSFER);
	   
		getKeypad().addObserver(new Observer() {
			int accountNumber = 0;
			
			@Override
			public void update(Observable observable) {
				int keyCode = getKeypad().getPressedKeyCode();
				
				switch (keyCode) {
				
				// enter				
				case Keypad.ENTER:
				case Keypad.RIGHT_KEY3:
					System.out.println("transfer account: " + accountNumber);					
				    System.out.println("[test]do Transfer...");
				    //code here...
					break;
					
				// cancel
				case Keypad.CANCEL:
				case Keypad.RIGHT_KEY4:
					exitTransaction();
					getScreen().getTransferScreen().clearAllMessages();
					break;
					
				// clear
				case Keypad.CLEAR:
					accountNumber = 0;
					getScreen().getTransferScreen().showMessage1(String.valueOf(accountNumber));					
					break;

				// others
				default:
					// keyCode in {0..9}
					if (0 <= keyCode && keyCode <= 9) {
						accountNumber = accountNumber * 10 + keyCode;
						getScreen().getTransferScreen().showMessage1(String.valueOf(accountNumber));
					}
					break;
					
				} // end switch (keyCode)
				
				System.out.println(String.valueOf("[transfer] key pressed: " + keyCode));
			} // end update()
		}); // end addObserver()
	} // end execute()
}
