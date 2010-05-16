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
import atm.input.DepositSlot;
import atm.input.Keypad;
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
	
	/** depositSlot **/
	private DepositSlot depositSlot = new DepositSlot();
	
	/** Database **/
	private BankDatabase bankDatabase = new BankDatabase();
	
	/** whether user is authenticated **/
	private boolean userAuthenticated;
	
	/** current user's account number **/
	private int currentAccountNumber;
	
	/** transactions collection **/
	private Map<Integer, Transaction> transactions;
	
	/** constructor **/
	public ATM() {
		super("ATM");
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
		vPanel1.add(depositSlot);		//depositSlot		
		
		JPanel vPanel2 = new JPanel(new GridLayout(0, 1));		
		vPanel2.add(cardSlot);			//cardSlot
		vPanel2.add(vPanel1);
		vPanel2.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		
		JPanel ioPanel = new JPanel();		
		ioPanel.add(keypad.getNumberKeypad());				//keypad - number keypad		
		ioPanel.add(keypad.getOperationKeypad()); 			//keypad - operation keypad		
		ioPanel.add(vPanel2);
		ioPanel.setBackground(new Color(51, 153, 204));
		
		add(new JLabel("Welcome to my ATM"), BorderLayout.NORTH); //welcome
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
	
	public void run() {
		while (true) {			
			while (!userAuthenticated) {
				screen.show(Screen.WELCOME);
				authenticateUser(cardSlot.getCardNumber());
			}
			
			performTransactions();
			userAuthenticated = false;
			currentAccountNumber = 0;
		}
	}
	
	private void authenticateUser(int cardNumber) {
		screen.show(Screen.LOGIN_MENU);
		screen.getLoginScreen().clearDisplay();

		int pin = keypad.readInput(Keypad.LOGIN_MODE);
		
		if (pin != CANCELED) {
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
			System.out.println("Cancelling authenticateUser...");
			cardSlot.ejectCard();
		}
	}
	
	private void performTransactions() {
		initTransactions();
		
		boolean userExited = false;
		
	    while (!userExited) {
		    screen.show(Screen.MAIN_MENU);
			
			int transactionType = keypad.readInput(Keypad.MENU_MODE);
			
			switch (transactionType) {
				case WITHDRAW:
				case BALANCE_INQUIRY:
				case CHANGE_PIN:
				case INSURANCE_COST_INFO:
				case REGISTER_SERVICE:
				case TRANSFER:
				case SERVICE_COST_INFO:					
					getTransaction(transactionType).execute();
					break;
					
				case CANCELED:
					userExited = true;
					cardSlot.ejectCard();
					break;
			} //end switch
	    } // end while
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
	
	private Transaction getTransaction(Integer type) {
		if (transactions.get(type) == null)
			return Transaction.newNull();
		return transactions.get(type);
	}

	private static final int WITHDRAW = Keypad.LEFT_KEY1;
	private static final int BALANCE_INQUIRY = Keypad.LEFT_KEY2;
	private static final int CHANGE_PIN = Keypad.LEFT_KEY3;
	private static final int INSURANCE_COST_INFO = Keypad.LEFT_KEY4;
	private static final int REGISTER_SERVICE = Keypad.RIGHT_KEY1;
	private static final int TRANSFER = Keypad.RIGHT_KEY2;
	private static final int SERVICE_COST_INFO = Keypad.RIGHT_KEY3;
	
	private static final int CANCELED = -1;
}
