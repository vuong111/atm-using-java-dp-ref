package atm.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import atm.gui.input.CardSlot;
import atm.gui.input.CashDispenser;
import atm.gui.input.DepositSlot;
import atm.gui.input.Keypad;
import atm.gui.observer.Observable;
import atm.gui.observer.Observer;
import atm.gui.removed.ScreenPanel;
import atm.gui.screen.LoginScreen;
import atm.gui.screen.Screen;
import atm.gui.welcome.WelcomePanel;
import atm.utils.ATMUtils;

public class ATMWindow extends JFrame {

	/**
	 * welcomePanel
	 */	
	private WelcomePanel welcomePanel = new WelcomePanel(this);
	
	/** screen **/
	private Screen screen = new Screen();
	
	/** keypad **/
	private Keypad keypad = new Keypad();
	
	/** cardSlot **/ 
	private CardSlot cardSlot = new CardSlot();
	
	/** cashDispenser **/
	private CashDispenser cashDispenser = new CashDispenser();
	
	/** depositSlot **/
	private DepositSlot depositSlot = new DepositSlot();
	
	/** Database **/
	
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
	}
	
	private void initComponents() {
		
		/**
		 * add components to the frame
		 */
		JPanel vPanel = new JPanel(new GridLayout(0, 1));		
		vPanel.add(cardSlot);			//cardSlot
		cardSlot.addObserver(new CardSlotListener());		
		vPanel.add(cashDispenser);		//cashDispenser
		vPanel.add(depositSlot);		//depositSlot
		vPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		
		JPanel ioPanel = new JPanel();		
		ioPanel.add(keypad.getNumberKeypad());				//keypad - number keypad		
		ioPanel.add(keypad.getOperationKeypad()); 			//keypad - operation keypad		
		ioPanel.add(vPanel);
		ioPanel.setBackground(new Color(51, 153, 204));
		
		add(welcomePanel, BorderLayout.NORTH);				//welcome
		add(keypad.getLeftKeypad(), BorderLayout.WEST);		//keypad - left side		
		add(keypad.getRightKeypad(), BorderLayout.EAST); 	//keypad - right side		
		add(screen, BorderLayout.CENTER); 				//screen		
		add(ioPanel, BorderLayout.SOUTH);
		
		/**
		 * pack()
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	
	public void run() {
		authenticateUser();
	}
	
	private void authenticateUser() {
		/** test cái coi :) **/
		final LoginScreen login = (LoginScreen) getScreen().getScreen(ScreenPanel.LOGIN_MENU);
		getKeypad().addObserver(new Observer() {
			private String accountNumber = "";
			private String pin = "";
			private int flag = 0;
			@Override
			public void update(Observable observable) {
				String key = getKeypad().getKeyPressed();
				if (ATMUtils.isNumberKey(key)) {
					if (flag == 0) {
						pin += key;
						login.updateAccountNumberField(key);
					}
					else {
						accountNumber += key;
						login.updatePINField(key);
					}
				}
				else if (key.equals(Keypad.ENTER) || key.equals(Keypad.RIGHT_KEY3)) {
					System.out.println("Account Number: " + accountNumber);
					System.out.println("PIN: " + pin);					
				}
				else if (key.equals(Keypad.CLEAR)) {
					if (flag == 0) {
						pin = "";
						login.updateAccountNumberField("");
					}
					else {
						accountNumber = "";
						login.updatePINField("");
					}
				}
				else if (key.equals(Keypad.RIGHT_KEY1)) {
					flag = 0;
				}
				else if (key.equals(Keypad.RIGHT_KEY2)) {
					flag = 1;
				}
				System.out.println(key);
			}

		});
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
	
	public CashDispenser getCashDispenser() {
		return cashDispenser;
	}
	
	public DepositSlot getDepositSlot() {
		return depositSlot;
	}
	
	public void getDatabase() {
		//
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
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               	ATMWindow atm = new ATMWindow();
               	atm.setVisible(true);
               	atm.run();
            }
        });
	}
	
	class CardSlotListener implements Observer {
		@Override
		public void update(Observable observable) {
			CardSlot cardSlot = (CardSlot) observable;
			System.out.println(cardSlot.isInserted());
			
		}
	}
}
