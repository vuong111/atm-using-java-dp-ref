package atm.gui;

import java.util.ArrayList;

import atm.gui.BankDatabase;
import atm.gui.input.Keypad;
import atm.gui.observer.Observable;
import atm.gui.observer.Observer;
import atm.gui.screen.Screen;

public abstract class Transaction implements Observable {
	private int accountNumber; // indicates account involved
   	private Screen screen; // ATM's screen
   	private BankDatabase bankDatabase; // account info database
   	private Keypad keypad; // ATM's keypad
   
	private ArrayList<Observer> observerList = new ArrayList<Observer>();

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
   
	/** exit the transaction **/
	public void exitTransaction() {
		notifyObservers();
	}
   
	/**
	 * implement Observable's methods
	 */
	
	@Override
	public void addObserver(Observer o) {
		deleteObservers();
		observerList.add(o);
		
	}
	
	@Override
	public void deleteObserver(Observer o) {
		observerList.remove(o);
		
	}
	
	@Override
	public void deleteObservers() {
		observerList = new ArrayList<Observer>();
		
	}
	
	@Override
	public void notifyObservers() {
		for (Observer o : observerList) {
			o.update(this);
		}
	}
}