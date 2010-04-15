package atm.gui;

import atm.gui.BankDatabase;
import atm.gui.input.CashDispenser;
import atm.gui.input.Keypad;
import atm.gui.observer.Observable;
import atm.gui.observer.Observer;
import atm.gui.screen.Screen;

public class Withdrawal extends Transaction {
	private static final double MONEY1 = 100;
	private static final double MONEY2 = 200;
	private static final double MONEY3 = 500;
	private static final double MONEY4 = 1000;
	private static final double MONEY5 = 2000;
	
	private double amount;
	
	private CashDispenser cashDispenser;
	public Withdrawal(int userAccountNumber, Screen atmScreen, 
			      BankDatabase atmBankDatabase, Keypad atmKeypad, 
			      CashDispenser atmCashDispenser) {
		
		super(userAccountNumber, atmScreen, atmBankDatabase, atmKeypad);  
		cashDispenser = atmCashDispenser;
	}
	
	@Override
	public void execute() {
	    getScreen().show(Screen.WITHDRAW_MENU);
	    
	    getKeypad().addObserver(new Observer() {
	    	double availableBalance;
		    BankDatabase bankDatabase = getBankDatabase(); 
		    
	    	@Override
			public void update(Observable observable) {
			
				int keyCode = getKeypad().getPressedKeyCode();
				
				switch (keyCode) {	
				
				case Keypad.RIGHT_KEY4:
				case Keypad.CANCEL:
					exitTransaction();
					break;
				default:
					amount = getAmount(keyCode);

					if (amount == 0) {
						return;
					}
					
					availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
					
					if (amount <= availableBalance) {
						bankDatabase.debit(getAccountNumber(), amount);
						System.out.println("Withdrew: " + amount);
						exitTransaction();
					}
					else
						System.out.println("Not enough money. Pls choose a smaller amount.");
				}
			}
	    	
	    	private double getAmount(int type) {
	    		double amount;
	    		switch (type) {
	    		case Keypad.LEFT_KEY1:
					amount = MONEY1;
					break;
				case Keypad.LEFT_KEY2:
					amount = MONEY3;
					break;
				case Keypad.LEFT_KEY3:
					amount = MONEY5;
					break;
				case Keypad.RIGHT_KEY1:
					amount = MONEY2;
					break;
				case Keypad.RIGHT_KEY2:
					amount = MONEY4;
					break;
				case Keypad.RIGHT_KEY3:
					amount = MONEY1;
					break;	
				default:
					amount = 0;
					break;
				}
	    		return amount;
	    	}
	    });
	}
}
