package atm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import atm.bank.BankDatabase;
import atm.input.CardSlot;
import atm.input.CashDispenser;
import atm.input.ReceiptSlot;
import atm.input.Keypad;
import atm.screen.ScreenType;
import atm.screen.Screen;
import atm.transaction.BalanceInquiry;
import atm.transaction.ChangePIN;
import atm.transaction.Transaction;
import atm.transaction.Transfer;
import atm.transaction.Withdrawal;

public class ATM extends JFrame {
	   
	/** screen **/
	private Screen screen = new Screen();
	
	/** keypad **/
	private Keypad keypad = new Keypad(screen);
	
	/** cardSlot **/ 
	private CardSlot cardSlot = new CardSlot();
	
	/** cashDispenser **/
	private CashDispenser cashDispenser = new CashDispenser();
	
	/** receiptSlot **/
	private ReceiptSlot receiptSlot = new ReceiptSlot();
	
	/** Database **/
	private BankDatabase bankDatabase = new BankDatabase();
	
	/** whether user is authenticated **/
	private boolean userAuthenticated;
	
	/** current user's account number **/
	private int currentAccountNumber;
	
	/** transactions collection -- stores transactions/commands **/
	private Map<Integer, Transaction> transactions;
	
	/** constructor **/
	public ATM() {
		super("ATM Machine");
		userAuthenticated = false;
		currentAccountNumber = 0;
		
		initComponents();		
	}
	
	private void initComponents() {		
		/**
		 * add components to the frame
		 */
		JPanel vPanel1 = new JPanel(new GridLayout(0, 1));		
		vPanel1.add(cashDispenser);		//cashDispenser
		vPanel1.add(receiptSlot);		//receiptSlot		
		
		JPanel vPanel2 = new JPanel(new GridLayout(0, 1));		
		vPanel2.add(cardSlot);			//cardSlot
		vPanel2.add(vPanel1);
		vPanel2.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		
		JPanel ioPanel = new JPanel();		
		ioPanel.add(keypad.getNumberKeypad());				//keypad - number keypad		
		ioPanel.add(keypad.getOperationKeypad()); 			//keypad - operation keypad		
		ioPanel.add(vPanel2);
		ioPanel.setBackground(Color.BLUE);
		
		//add(new JLabel("Welcome to my ATM"), BorderLayout.NORTH); //welcome
		add(keypad.getLeftKeypad(), BorderLayout.WEST);		//keypad - left side		
		add(keypad.getRightKeypad(), BorderLayout.EAST); 	//keypad - right side		
		add(screen, BorderLayout.CENTER); 				  	//screen		
		add(ioPanel, BorderLayout.SOUTH);
		
		/**
		 * pack()
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	
	private void initTransactions() {
		transactions = new HashMap<Integer, Transaction>();
		
		transactions.put(WITHDRAW, 
				new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser));
		transactions.put(BALANCE_INQUIRY, 
				new BalanceInquiry(currentAccountNumber, screen, bankDatabase, keypad));
		transactions.put(CHANGE_PIN, 
				new ChangePIN(currentAccountNumber, screen, bankDatabase, keypad));
		transactions.put(TRANSFER, 
				new Transfer(currentAccountNumber, screen, bankDatabase, keypad));
	}
	
	private Transaction lookupTransactionBy(Integer type) {
		if (transactions.get(type) == null)
			return Transaction.newNull();
		return transactions.get(type);
	}
	
	public void run() {
		while (true) {
			while (!userAuthenticated) {
				screen.setType(ScreenType.WELCOME_TYPE);
				authenticateUser(cardSlot.getCardNumber());
			}
			
			performTransactions();
			userAuthenticated = false;
			currentAccountNumber = 0;
		}
	}
	
	private void authenticateUser(int cardNumber) {
		screen.setType(ScreenType.LOGIN_TYPE);
		screen.clearDisplay();

		int pin = keypad.readInput(Keypad.LOGIN_MODE);
		
		if (pin != CANCELLED) {
			userAuthenticated = bankDatabase.authenticateUser(cardNumber, pin);				      

		    if (userAuthenticated) {
		        currentAccountNumber = cardNumber;
		        userAuthenticated = true;
		    }
		    else {
		    	System.out.println("Invalid PIN: " + pin);
		    	cardSlot.ejectCard();
		    }
		}
		else {
			System.out.println("AuthenticateUser cancelled..");
			cardSlot.ejectCard();
		}
	}
	
	private void performTransactions() {	
		initTransactions(); //init Commands
		
		boolean userExited = false;
		
	    while (!userExited) {
		    screen.setType(ScreenType.MAIN_MENU_TYPE);
			
			int transactionType = keypad.readInput(Keypad.MENU_MODE);
			
			switch (transactionType) {
				case WITHDRAW:
				case BALANCE_INQUIRY:
				case CHANGE_PIN:
				case MINI_STATEMENT:
				case TRANSFER:
					/* Refactoring > Replace Conditional Dispatcher with Command */
					Transaction transaction = lookupTransactionBy(transactionType);
					transaction.execute();
					break;
					
				case CANCELLED:
					userExited = true;
					cardSlot.ejectCard();
					System.out.println("performTransactions cancelled..");
					break;
			} //end switch
	    } // end while
	}	

	private static final int WITHDRAW = Keypad.LEFT_KEY1;
	private static final int BALANCE_INQUIRY = Keypad.LEFT_KEY2;
	private static final int CHANGE_PIN = Keypad.LEFT_KEY3;
	private static final int MINI_STATEMENT = Keypad.RIGHT_KEY1;
	private static final int TRANSFER = Keypad.RIGHT_KEY2;
	
	private static final int CANCELLED = Keypad.CANCELLED;
}
