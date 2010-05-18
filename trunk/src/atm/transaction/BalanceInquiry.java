package atm.transaction;

import atm.bank.BankDatabase;
import atm.input.Keypad;
import atm.screen.Screen;
import atm.screen.ScreenController;

public class BalanceInquiry extends Transaction
{
	/** BalanceInquiry constructor **/
	public BalanceInquiry(int userAccountNumber, ScreenController atmScreen, 
						BankDatabase atmBankDatabase, Keypad atmKeypad) {
		
		super(userAccountNumber, atmScreen, atmBankDatabase, atmKeypad);
	}

	/** performs the transaction **/
	public synchronized void execute()
	{
		String fullName = getBankDatabase().getFullName(getAccountNumber());      
		double availableBalance = getBankDatabase().getAvailableBalance( getAccountNumber() );

		getScreenController().showScreen(Screen.VIEW_BALANCE);

		getScreenController().printMessage(getAccountNumber() + "", 1);
		getScreenController().printMessage(fullName, 2);
		getScreenController().printMessage(availableBalance + " VND", 3);
		
		//wait until enter/cancel key is clicked
		waitForEscape(); //or call getKeypad().readInput(Keypad.BALANCE_INQUIRY_MODE);
	}
	
	private void waitForEscape() {
		synchronized (getKeypad()) {
			try {
				getKeypad().wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}