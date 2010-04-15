package atm.gui;

import atm.gui.input.Keypad;
import atm.gui.observer.Observable;
import atm.gui.observer.Observer;
import atm.gui.screen.Screen;

public class BalanceInquiry extends Transaction
{
	/** BalanceInquiry constructor **/
	public BalanceInquiry(int userAccountNumber, Screen atmScreen, 
						BankDatabase atmBankDatabase, Keypad atmKeypad) {
		
		super(userAccountNumber, atmScreen, atmBankDatabase, atmKeypad);
	}

	/** performs the transaction **/
	public void execute()
	{
		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();
      
		String fullName = bankDatabase.getFullName( getAccountNumber() );      
		double availableBalance = bankDatabase.getAvailableBalance( getAccountNumber() );

		screen.show(Screen.VIEW_BALANCE);
		//should use MOVE METHOD for the following code??
		screen.getViewBalanceScreen().displayMessage1(getAccountNumber() + "");
		screen.getViewBalanceScreen().displayMessage2(fullName);
		screen.getViewBalanceScreen().displayMessage3(availableBalance + " VND");
	   
		getKeypad().addObserver(new Observer() {
			@Override
			public void update(Observable observable) {
				if ((getKeypad().getPressedKeyCode() == Keypad.RIGHT_KEY4) ||
						(getKeypad().getPressedKeyCode() == Keypad.CANCEL)) {
					
					exitTransaction();
				}
			}
		});
	}
}