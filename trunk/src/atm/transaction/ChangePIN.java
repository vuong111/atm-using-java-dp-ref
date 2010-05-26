package atm.transaction;

import atm.bank.BankDatabase;
import atm.input.Keypad;
import atm.screen.ScreenType;
import atm.screen.Screen;

public class ChangePIN extends Transaction {
	
	private static final int CANCELLED = -1;
	
	private int newPIN;
	
	/** ChangePIN constructor **/
	public ChangePIN(int userAccountNumber, Screen atmScreen, 
						BankDatabase atmBankDatabase, Keypad atmKeypad) {
		
		super(userAccountNumber, atmScreen, atmBankDatabase, atmKeypad);
	}

	/** performs the transaction **/
	public void execute()
	{
		//nhập mã PIN mới
		newPIN = inputNewPINCode();		
		
		if (!newPINIsValid())
			return;
		
		//nhập lại mã PIN để xác nhận
		int newPIN_confirm = inputNewPINCode_confim();	
		
		if (newPIN_confirm != newPIN) {
			System.out.println("2 PIN fields do not match..");
			return;
		}
		
		//thực hiện đổi PIN
		performChangePIN();
	}

	private void performChangePIN() {
		getBankDatabase().changePIN(getAccountNumber(), newPIN);
	    System.out.println("PIN changed..");
	}

	private boolean newPINIsValid() {
		if (newPIN == CANCELLED) {
			System.out.println("ChangePIN cancelled..");
			return false;
		}
		
		if (newPIN < 100000 || newPIN > 999999) {
			System.out.println("PIN must have 6 digits");
			return false;
		}
		
		return true;
	}

	private int inputNewPINCode() {
		getScreen().setType(ScreenType.CHANGE_PIN1_TYPE);
		getScreen().clearDisplay();
		
		return getKeypad().readInput(Keypad.CHANGE_PIN_MODE);
	}
	
	private int inputNewPINCode_confim() {
		getScreen().setType(ScreenType.CHANGE_PIN2_TYPE);
		getScreen().clearDisplay();
		
		return getKeypad().readInput(Keypad.CHANGE_PIN_MODE);
	}
}
