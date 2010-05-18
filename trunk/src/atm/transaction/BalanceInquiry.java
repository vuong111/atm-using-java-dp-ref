package atm.transaction;

import atm.bank.BankDatabase;
import atm.input.Keypad;
import atm.screen.ScreenType;
import atm.screen.Screen;

public class BalanceInquiry extends Transaction
{
	/** BalanceInquiry constructor **/
	public BalanceInquiry(int userAccountNumber, Screen atmScreen, 
						BankDatabase atmBankDatabase, Keypad atmKeypad) {
		
		super(userAccountNumber, atmScreen, atmBankDatabase, atmKeypad);
	}

	/** performs the transaction **/
	public synchronized void execute()
	{
		String fullName = getBankDatabase().getFullName(getAccountNumber());      
		double availableBalance = getBankDatabase().getAvailableBalance( getAccountNumber() );

		getScreen().setScreenType(ScreenType.VIEW_TYPE);

		getScreen().printMessage(getAccountNumber() + "", 1);
		getScreen().printMessage(fullName, 2);
		getScreen().printMessage(availableBalance + " VND", 3);
		
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