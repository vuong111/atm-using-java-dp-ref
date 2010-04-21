package atm.gui;

import atm.gui.input.Keypad;
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
		getScreen().show(Screen.CHANGE_PIN);
		
		System.out.println("Under construction");
	   
//		getKeypad().addObserver(new Observer() {
//			static final int PIN_IS_SELECTED = 0;
//			static final int PIN_CONFIRM_IS_SELECTED = 1;
//			
//			int flag = PIN_IS_SELECTED;
//			int newPIN = 0;
//			int newPINConfirm = 0;
//			
//			@Override
//			public void update(Observable observable) {			
//				int keyCode = getKeypad().getPressedKeyCode();
//				
//				switch (keyCode) {
//				
//				// enter				
//				case Keypad.ENTER:
//				case Keypad.RIGHT_KEY3:
//					System.out.println("newPIN: " + newPIN);
//					System.out.println("newPINConfirm: " + newPINConfirm);		
//
//				    if (newPIN == newPINConfirm && newPIN != 0) {
//				    	getBankDatabase().changePIN(getAccountNumber(), newPIN);				    	
//				    	System.out.println("PIN changed okii");
//				    	
//				    	exitTransaction();
//				    	getScreen().getChangePINScreen().clearAllMessages();
//				    }
//				    else
//				    	System.out.println("2 PIN fields do not match.. / PIN must <> 0");
//					break;
//					
//				// cancel
//				case Keypad.CANCEL:
//				case Keypad.RIGHT_KEY4:
//					exitTransaction();
//					getScreen().getChangePINScreen().clearAllMessages();
//					break;
//					
//				// clear
//				case Keypad.CLEAR:
//					if (flag == PIN_IS_SELECTED) {
//						newPIN = 0;
//						getScreen().getChangePINScreen().showMessage1(String.valueOf(newPIN));
//					}
//					else {
//						newPINConfirm = 0;
//						getScreen().getChangePINScreen().showMessage2(String.valueOf(newPINConfirm));
//					}
//					break;
//				
//				//right key 1
//				case Keypad.RIGHT_KEY1:
//					flag = PIN_IS_SELECTED;
//					break;
//				
//				//right key 2
//				case Keypad.RIGHT_KEY2:
//					flag = PIN_CONFIRM_IS_SELECTED;
//					break;
//					
//				// others
//				default:
//					// keyCode in {0..9}
//					if (0 <= keyCode && keyCode <= 9) {
//						if (flag == PIN_IS_SELECTED) {
//							newPIN = newPIN * 10 + keyCode;
//							getScreen().getChangePINScreen().showMessage1(String.valueOf(newPIN));
//						}
//						else {
//							newPINConfirm = newPINConfirm * 10 + keyCode;
//							getScreen().getChangePINScreen().showMessage2(String.valueOf(newPINConfirm));
//						}
//					}
//					break;			
//					
//				} // end switch (keyCode)
//				
//				System.out.println(String.valueOf("[changePIN] key pressed: " + keyCode));
//				
//			} // end update()
//		}); // end addObserver()
	} // end execute()
}
