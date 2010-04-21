package atm.gui;

import atm.gui.BankDatabase;
import atm.gui.input.CashDispenser;
import atm.gui.input.Keypad;
import atm.gui.screen.Screen;

public class Withdrawal extends Transaction {
	private static final double MONEY1 = 100;
	private static final double MONEY2 = 200;
	private static final double MONEY3 = 500;
	private static final double MONEY4 = 1000;
	private static final double MONEY5 = 2000;
	private static final double MONEY6 = 2000;
	
	private final static int CANCELED = -1;
	
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
	    
	    boolean cashDispensed = false; // cash was not dispensed yet
	    double availableBalance; // amount available for withdrawal
	      
	    double amountOptions[] = {MONEY1, MONEY2, MONEY3, 0, MONEY4, MONEY5, MONEY6};
	    
	    do {
		    getScreen().show(Screen.WITHDRAW_MENU);
		    
		    int choice = getKeypad().readInput(Keypad.WITHDRAW_MODE);		    
		    
		    if (choice != CANCELED) {
		    	amount = amountOptions[choice - 1];
			    availableBalance = getBankDatabase().getAvailableBalance(getAccountNumber());
				
				if (amount <= availableBalance) {
					getBankDatabase().debit(getAccountNumber(), amount);
					cashDispensed = true;
					System.out.println("cashWithdrew: " + amount);
				}
				else
					System.out.println("Not enough money. Pls choose a smaller amount.");
		    }
		    else {
		    	System.out.println("Cancelling withdraw..." );
	            return;
		    }
	    } while (!cashDispensed);		

	} // end execute()
}
