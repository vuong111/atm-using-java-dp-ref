package atm.transaction;

import atm.bank.BankDatabase;
import atm.input.Keypad;
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
		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();
      
		String fullName = bankDatabase.getFullName(getAccountNumber());      
		double availableBalance = bankDatabase.getAvailableBalance( getAccountNumber() );

		screen.show(Screen.VIEW_BALANCE);
		//should use MOVE METHOD for the following code??
		screen.getViewBalanceScreen().displayMessage1(getAccountNumber() + "");
		screen.getViewBalanceScreen().displayMessage2(fullName);
		screen.getViewBalanceScreen().displayMessage3(availableBalance + " VND");
		
		getKeypad().readInput(Keypad.BALANCE_INQUIRY_MODE);
	}
}