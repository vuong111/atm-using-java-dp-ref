//package atm.gui.removed;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.GridLayout;
//import java.util.concurrent.TimeUnit;
//
//import javax.swing.BorderFactory;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.SwingUtilities;
//
//import atm.gui.BalanceInquiry;
//import atm.gui.BankDatabase;
//import atm.gui.ChangePIN;
//import atm.gui.Transaction;
//import atm.gui.Transfer;
//import atm.gui.Withdrawal;
//import atm.gui.input.CardSlot;
//import atm.gui.input.CashDispenser;
//import atm.gui.input.DepositSlot;
//import atm.gui.input.Keypad;
//import atm.gui.observer.ExitObserver;
//import atm.gui.observer.Observable;
//import atm.gui.observer.Observer;
//import atm.gui.screen.Screen;
//import atm.gui.welcome.WelcomePanel;
//
//public class ATMWindow extends JFrame {
//
//	private static final int WITHDRAW = 10;
//	private static final int BALANCE_INQUIRY = 11;
//	private static final int CHANGE_PIN = 12;
//	//13 14
//	private static final int TRANSFER = 15;
//	//16
//	private static final int EXIT = 17;
//	   
//	/**
//	 * welcomePanel
//	 */	
//	//private WelcomePanel welcomePanel = new WelcomePanel(this);
//	
//	/** screen **/
//	private Screen screen = new Screen();
//	
//	/** keypad **/
//	private Keypad keypad = new Keypad();
//	
//	/** cardSlot **/ 
//	private CardSlot cardSlot = new CardSlot();
//	
//	/** cashDispenser **/
//	private CashDispenser cashDispenser = new CashDispenser();
//	
//	/** depositSlot **/
//	private DepositSlot depositSlot = new DepositSlot();
//	
//	/** Database **/
//	private BankDatabase bankDatabase = new BankDatabase();
//	
//	/** whether user is authenticated **/
//	private boolean userAuthenticated;
//	
//	/** current user's account number **/
//	private int currentAccountNumber;
//	
//	/** constructor **/
//	public ATMWindow() {
//		super("ATM");
//		userAuthenticated = false;
//		currentAccountNumber = 0;
//		
//		initComponents();
//		System.out.println("[ATMWindow constructor]: " + Thread.currentThread());
//	}
//	
//	private void initComponents() {		
//		/**
//		 * add components to the frame
//		 */
//		JPanel vPanel = new JPanel(new GridLayout(0, 1));		
//		vPanel.add(cardSlot);			//cardSlot
//		cardSlot.addObserver(new CardSlotListener());		
//		vPanel.add(cashDispenser);		//cashDispenser
//		vPanel.add(depositSlot);		//depositSlot
//		vPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
//		
//		JPanel ioPanel = new JPanel();		
//		ioPanel.add(keypad.getNumberKeypad());				//keypad - number keypad		
//		ioPanel.add(keypad.getOperationKeypad()); 			//keypad - operation keypad		
//		ioPanel.add(vPanel);
//		ioPanel.setBackground(new Color(51, 153, 204));
//		
//		//add(welcomePanel, BorderLayout.NORTH);				//welcome
//		add(keypad.getLeftKeypad(), BorderLayout.WEST);		//keypad - left side		
//		add(keypad.getRightKeypad(), BorderLayout.EAST); 	//keypad - right side		
//		add(screen, BorderLayout.CENTER); 				  	//screen		
//		add(ioPanel, BorderLayout.SOUTH);
//		
//		/**
//		 * pack()
//		 */
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		pack();
//	}
//	
//	public void run() {
//		authenticateUser();
//		new Thread() {
//			@Override
//			public void run() {
//				while (!userAuthenticated) {
//					try {
//						TimeUnit.SECONDS.sleep(1);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					System.out.println("[run()]: " + Thread.currentThread());
//				}
//				performTransactions();
//			}
//		}.start();
//	}
//	
//	private void authenticateUser() {
//		screen.show(Screen.LOGIN_MENU);
//		keypad.addObserver(new Observer() {
//			static final int ACCOUNT_FIELD_SELECTED = 0;
//			static final int PIN_FIELD_SELECTED = 1;
//			
//			int accountNumber = 12345;
//			int pin = 54321;
//			int flag = ACCOUNT_FIELD_SELECTED;
//			
//			@Override
//			public void update(Observable observable) {
//				int keyCode = getKeypad().getPressedKeyCode();
//				
//				switch (keyCode) {
//							
//				case Keypad.ENTER:
//				case Keypad.RIGHT_KEY3:
//					System.out.println("Account number: " + accountNumber);
//					System.out.println("PIN: " + pin);
//					userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);				      
//
//				    if (userAuthenticated) {
//				        currentAccountNumber = accountNumber;
//				        userAuthenticated = true;
//				        
//				        //screen.getLoginScreen().clearAllMessages();
//				    }
//				    else
//				    	System.out.println("Invalid account number or PIN. Please try again.");
//					break;
//					
//				case Keypad.CLEAR:
//					if (flag == ACCOUNT_FIELD_SELECTED) {
//						accountNumber = 0;
//						screen.getLoginScreen().showMessage1(String.valueOf(accountNumber));
//					}
//					else {
//						pin = 0;
//						screen.getLoginScreen().showMessage2(String.valueOf(pin));
//					}
//					break;
//					
//				case Keypad.RIGHT_KEY1:
//					flag = ACCOUNT_FIELD_SELECTED;
//					break;
//					
//				case Keypad.RIGHT_KEY2:
//					flag = PIN_FIELD_SELECTED;
//					break;
//				
//				default: 
//					if (0 <= keyCode && keyCode <= 9) {				
//						if (flag == ACCOUNT_FIELD_SELECTED) {
//							accountNumber = accountNumber * 10 + keyCode;
//							screen.getLoginScreen().showMessage1(String.valueOf(accountNumber));
//						}
//						else {
//							pin = pin * 10 + keyCode;
//							screen.getLoginScreen().showMessage2(String.valueOf(pin));
//						}
//					}
//					break;
//				}
//				
//				System.out.println(String.valueOf("[authenticateUser] key pressed: " + keyCode));
//			}
//
//		});
//	}
//	
//	private void performTransactions() {
//		screen.show(Screen.MAIN_MENU);
//		keypad.addObserver(new Observer() {
//			@Override
//			public void update(Observable observable) {	
//				System.out.println("keypad's Observer: " + Thread.currentThread());
//				int keyCode = keypad.getPressedKeyCode();
//				switch (keyCode) {
//				case WITHDRAW:
//				case BALANCE_INQUIRY:
//				case CHANGE_PIN:
//				case TRANSFER:
//					Transaction transaction = createTransaction(keyCode);
//					if (transaction != null) {
//						transaction.execute();
//						transaction.addObserver(new ExitObserver() {
//							@Override
//							public void update(Observable observable) {
//								performTransactions();	
//							}
//						});
//					}
//					break;
//				case EXIT:
//					System.out.println("[performTransactions]exit.. restart()");
//					
//					userAuthenticated = false;
//					run();
//				default:
//					break;
//				}
//			}
//		});
//	}
//	
//	private Transaction createTransaction( int type ) {
//		Transaction transaction = null;
//	      
//	     // determine which type of Transaction to create
//	    switch (type) {
//	    case WITHDRAW: //Keypad.LEFT_KEY1:
//	    	transaction = new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser);
//	    	break;
//	    case BALANCE_INQUIRY: //Keypad.LEFT_KEY2:
//	    	transaction = new BalanceInquiry(currentAccountNumber, screen, bankDatabase, keypad);
//            break;
//	    case CHANGE_PIN: //Keypad.LEFT_KEY3:
//	    	transaction = new ChangePIN(currentAccountNumber, screen, bankDatabase, keypad);
//	    	break;
//	    case TRANSFER: //Keypad.RIGHT_KEY2:
//	    	transaction = new Transfer(currentAccountNumber, screen, bankDatabase, keypad);
//	    	break;
//	    }
//
//	    return transaction;
//   }
//	
////	public WelcomePanel getWelcomePanel() {
////		return welcomePanel;
////	}
//	
//	public Screen getScreen() {
//		return screen;
//	}
//	
//	public Keypad getKeypad() {
//		return keypad;
//	}
//	
//	public CashDispenser getCashDispenser() {
//		return cashDispenser;
//	}
//	
//	public DepositSlot getDepositSlot() {
//		return depositSlot;
//	}
//	
//	public BankDatabase getDatabase() {
//		return bankDatabase;
//	}
//	
//	public boolean getUserAuthenticated() {
//		return userAuthenticated;
//	}
//	
//	public int getCurrentAccountNumber() {
//		return currentAccountNumber;
//	}
//	
//	public void setUserAuthenticated(Boolean authenticated) {
//		userAuthenticated = authenticated;
//	}
//	
//	public void setCurrentAccountNumber(int accountNumber) {
//		currentAccountNumber = accountNumber;
//	}
//	
//	public static void main(String[] args) {
//		System.out.println("[main()]: " + Thread.currentThread());
////		SwingUtilities.invokeLater(new Runnable() {
////            public void run() {
//            	System.out.println("[invokeLater..]: " + Thread.currentThread());
//               	ATMWindow atm = new ATMWindow();
//               	atm.setVisible(true);
//               	atm.run();
////            }
////        });
//		System.out.println("======[main()]======");
//	}
//	
//	class CardSlotListener implements Observer {
//		@Override
//		public void update(Observable observable) {
//			CardSlot cardSlot = (CardSlot) observable;
//			System.out.println(cardSlot.isInserted());			
//		}
//	}
//}
