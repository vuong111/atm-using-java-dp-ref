package atm.transaction;

import atm.bank.BankDatabase;
import atm.input.CashDispenser;
import atm.input.Keypad;
import atm.screen.ScreenType;
import atm.screen.Screen;

public class Withdrawal extends Transaction {
	private static final int AMOUNT1 = 50;
	private static final int AMOUNT2 = 100;
	private static final int AMOUNT3 = 200;
	private static final int AMOUNT4 = 500;
	private static final int AMOUNT5 = 1000;
	private static final int AMOUNT6 = 1500;
	private static final int AMOUNT7 = 2000;
	
	private final static int CANCELLED = -1;
	
	private int amount;
	
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

	    int amountOptions[] = {AMOUNT1, AMOUNT2, AMOUNT3, AMOUNT4, AMOUNT5, AMOUNT6, AMOUNT7};
	    
	    do {
		    getScreen().setType(ScreenType.WITHDRAW1_TYPE);
		    
		    int choice = getKeypad().readInput(Keypad.WITHDRAW_MODE);
		    
		    if (choice != CANCELLED) {
		    	amount = amountOptions[choice - 1];
			    availableBalance = getBankDatabase().getAvailableBalance(getAccountNumber());
				
				if (amount <= availableBalance) {
					if (cashDispenser.isSufficientCashAvailable(amount)) {
						getBankDatabase().debit(getAccountNumber(), amount);
						
						cashDispenser.dispenseCash(amount);
						cashDispensed = true;
						
						System.out.println("cashWithdrew: " + amount);
					}
					else
						System.out.println("Cash is not available in the ATM. Please choose a smaller amount.");
				}
				else
					System.out.println("Not enough money . Please choose a smaller amount.");
		    }
		    else {
		    	System.out.println("Withdraw cancelled.." );
	            return;
		    }
	    } while (!cashDispensed);

	} // end execute()
}
