package atm.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import atm.gui.input.CashDispenser;
import atm.gui.input.DepositSlot;
import atm.gui.input.KeypadPanel;
import atm.gui.screen.ScreenPanel;
import atm.gui.welcome.WelcomePanel;

public class ATMWindow extends JFrame {

	/**
	 * screenPanel
	 */	
	private ScreenPanel screenPanel = new ScreenPanel(this);
	
	/**
	 * welcomePanel
	 */	
	private WelcomePanel welcomePanel = new WelcomePanel(this);
	
	/**
	 * keypadPanel
	 */
	private KeypadPanel keypadPanel = new KeypadPanel(this);
	
	/**
	 * cashDispenser
	 */
	private CashDispenser cashDispenser = new CashDispenser();
	
	/**
	 * depositSlot
	 */
	private DepositSlot depositSlot = new DepositSlot();
	
	/**
	 * Database
	 */
	
	/**
	 * whether user is authenticated
	 */
	private boolean userAuthenticated;
	
	/**
	 * current user's account number
	 */
	private int currentAccountNumber;
	
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
		add(welcomePanel, BorderLayout.NORTH);
		add(screenPanel, BorderLayout.CENTER);
		
		Box vBox = Box.createVerticalBox();
		vBox.add(cashDispenser);
			cashDispenser.setAlignmentX(CENTER_ALIGNMENT);
		vBox.add(depositSlot);
			depositSlot.setAlignmentX(CENTER_ALIGNMENT);
			
		JPanel ioPanel = new JPanel();
		ioPanel.add(keypadPanel);
		ioPanel.add(vBox);
		ioPanel.setBackground(new Color(51, 153, 204));
		
		add(ioPanel, BorderLayout.SOUTH);
		
		/**
		 * pack()
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	
	public void run() {
		getScreenPanel().displayPanel(ScreenPanel.LOGIN_MENU);
	}	
	
	public WelcomePanel getWelcomePanel() {
		return welcomePanel;
	}
	
	public ScreenPanel getScreenPanel() {
		return screenPanel;
	}
	
	public KeypadPanel getKeypadPanel() {
		return keypadPanel;
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
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               	ATMWindow atm = new ATMWindow();
               	atm.setVisible(true);
               	atm.run();
            }
        });
	}
}
