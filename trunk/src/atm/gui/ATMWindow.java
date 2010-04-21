package atm.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import atm.gui.BalanceInquiry;
import atm.gui.Transaction;
import atm.gui.input.CardSlot;
import atm.gui.input.CashDispenser;
import atm.gui.input.DepositSlot;
import atm.gui.input.Keypad;
import atm.gui.screen.Screen;
import atm.gui.welcome.WelcomePanel;

public class ATMWindow extends JFrame {
	   
	/**
	 * welcomePanel
	 */	
	private WelcomePanel welcomePanel = new WelcomePanel(this);
	
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
	
	/** constructor **/
	public ATMWindow() {
		super("ATM");
		userAuthenticated = false;
		currentAccountNumber = 0;
		
		initComponents();
		System.out.println("[ATMWindow constructor]: " + Thread.currentThread());
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
		
		add(welcomePanel, BorderLayout.NORTH);				//welcome
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
		    	System.out.println("Valid PIN: " + pin);
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
		
		Transaction currentTransaction = null;	      
	    boolean userExited = false;
		
	    while (!userExited) {
		    screen.show(Screen.MAIN_MENU);
			
			int mainMenuSelection = keypad.readInput(Keypad.MENU_MODE);
			
			switch (mainMenuSelection) {
				case WITHDRAW:
				case BALANCE_INQUIRY:
				case CHANGE_PIN:
				case INSURANCE_COST_INFO:
				case REGISTER_SERVICE:
				case TRANSFER:
				case SERVICE_COST_INFO:
					currentTransaction = createTransaction(mainMenuSelection);	
					if (currentTransaction != null)
						currentTransaction.execute(); // execute transaction
					else
						System.out.println("Under construction...");
		            break;

				case CANCELED:
					userExited = true;
					cardSlot.ejectCard();
					System.out.println("Cancelling performTransactions..");
					break;
	
				default:
					System.out.println("invalid selection...");
					break;
			} //end switch
	    } // end while
	}
	
	private Transaction createTransaction( int type ) {
		Transaction transaction = null;
	      
	     // determine which type of Transaction to create
	    switch (type) {
	    case WITHDRAW: //Keypad.LEFT_KEY1:
	    	transaction = new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser);
	    	break;
	    	
	    case BALANCE_INQUIRY: //Keypad.LEFT_KEY2:
	    	transaction = new BalanceInquiry(currentAccountNumber, screen, bankDatabase, keypad);
            break;
            
	    case CHANGE_PIN: //Keypad.LEFT_KEY3:
	    	transaction = new ChangePIN(currentAccountNumber, screen, bankDatabase, keypad);
	    	break;
	   
	    case INSURANCE_COST_INFO: //Keypad.LEFT_KEY4:
	    	break;
	    	
	    case REGISTER_SERVICE: //Keypad.RIGHT_KEY1:
	    	break;
	    
	    case TRANSFER: //Keypad.RIGHT_KEY2:
	    	transaction = new Transfer(currentAccountNumber, screen, bankDatabase, keypad);
	    	break;
	    	
	    case SERVICE_COST_INFO: //Keypad.RIGHT_KEY3:
	    	break;
	    }

	    return transaction;
   }
	
	public WelcomePanel getWelcomePanel() {
		return welcomePanel;
	}
	
	public Screen getScreen() {
		return screen;
	}
	
	public Keypad getKeypad() {
		return keypad;
	}
	
	public CardSlot getCardSlot() {
		return cardSlot;
	}
	
	public CashDispenser getCashDispenser() {
		return cashDispenser;
	}
	
	public DepositSlot getDepositSlot() {
		return depositSlot;
	}
	
	public BankDatabase getDatabase() {
		return bankDatabase;
	}
	
	public boolean getUserAuthenticated() {
		return userAuthenticated;
	}
	
	public int getCurrentAccountNumber() {
		return currentAccountNumber;
	}
	
	public void setUserAuthenticated(Boolean authenticated) {
		userAuthenticated = authenticated;
	}
	
	public void setCurrentAccountNumber(int accountNumber) {
		currentAccountNumber = accountNumber;
	}
	
	public static void main(String[] args) {
		System.out.println("[main()]: " + Thread.currentThread());
//		SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
            	System.out.println("[invokeLater..]: " + Thread.currentThread());
               	ATMWindow atm = new ATMWindow();
               	atm.setVisible(true);
               	atm.run();
//            }
//        });
		System.out.println("======[main()]======");
	}	

	private static final int WITHDRAW = 1; //= Keypad.LEFT_KEY1
	private static final int BALANCE_INQUIRY = 2; //= Keypad.LEFT_KEY2
	private static final int CHANGE_PIN = 3; //= Keypad.LEFT_KEY3
	private static final int INSURANCE_COST_INFO = 4; //= Keypad.LEFT_KEY4
	private static final int REGISTER_SERVICE = 5; //= Keypad.RIGHT_KEY1
	private static final int TRANSFER = 6; //= Keypad.RIGHT_KEY2
	private static final int SERVICE_COST_INFO = 7; //= Keypad.RIGHT_KEY3
	
	private static final int CANCELED = -1;
}
