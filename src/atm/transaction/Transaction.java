package atm.transaction;

import java.util.ArrayList;

import atm.bank.BankDatabase;
import atm.input.Keypad;
import atm.screen.Screen;

public abstract class Transaction {
	private int accountNumber; // indicates account involved
   	private Screen screen; // ATM's screen
   	private BankDatabase bankDatabase; // account info database
   	private Keypad keypad; // ATM's keypad
   
	public Transaction() {}
	
	/** Transaction constructor invoked by subclasses using super() **/
	public Transaction(int userAccountNumber, Screen atmScreen, 
						BankDatabase atmBankDatabase, Keypad atmKeypad) {
		
		accountNumber = userAccountNumber;
		screen = atmScreen;
		bankDatabase = atmBankDatabase;
		keypad = atmKeypad;
	}

	/** return account number **/ 
	public int getAccountNumber() {
		return accountNumber;
	}

	/** return reference to screen **/
	public Screen getScreen() {
		return screen;
	}

	/** return reference to bank database **/
	public BankDatabase getBankDatabase() {
		return bankDatabase;
	}

	/** return reference to keypad **/
	public Keypad getKeypad() {
		return keypad;
	}
   
	/** perform the transaction (overridden by each subclass) **/
	abstract public void execute();
	
	/** Introduce Null Ojbect.. **/
	public static Transaction newNull() {
		return new NullTransaction();
	}
}