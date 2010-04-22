package atm.transaction;

import atm.bank.BankDatabase;
import atm.input.Keypad;
import atm.screen.Screen;
import atm.screen.TransferScreen;

public class Transfer extends Transaction {
	
	private static final int CANCELLED = -1;
	
	/** Transfer constructor **/
	public Transfer(int userAccountNumber, Screen atmScreen, 
						BankDatabase atmBankDatabase, Keypad atmKeypad) {
		
		super(userAccountNumber, atmScreen, atmBankDatabase, atmKeypad);
	}

	/** performs the transaction **/
	public void execute()
	{
		getScreen().show(Screen.TRANSFER);
		
		getScreen().getTransferScreen().showPanel(TransferScreen.ACCOUNT_PANEL);
		int transferAccount = getKeypad().readInput(Keypad.TRANSFER_MODE);
		getScreen().getTransferScreen().clearDisplay();

		if (transferAccount == CANCELLED) {
			return;
		}
		
		getScreen().getTransferScreen().showPanel(TransferScreen.MONEY_PANEL);
		int transferMoney = getKeypad().readInput(Keypad.TRANSFER_MODE);
		getScreen().getTransferScreen().clearDisplay();
		
		if (transferMoney == CANCELLED) {
			return;
		}
		
		System.out.println("[Under construction] - " + transferAccount + " / " + transferMoney);
	}
}
