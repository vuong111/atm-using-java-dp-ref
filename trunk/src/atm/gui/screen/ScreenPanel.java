package atm.gui.screen;

import java.awt.CardLayout;
import javax.swing.JPanel;

import atm.gui.ATMWindow;

/**
 * II - screenPanel
 */
public class ScreenPanel extends JPanel {

	public static final String LOGIN_MENU = "Login Menu";
	public static final String MAIN_MENU = "Main Menu";
	public static final String WITHDRAW_MENU = "Withdraw Menu";
	public static final String VIEW_BALANCE = "View Balance";
	
	/**
	 * loginPanel - panel cho màn hình đăng nhập
	 */
	private LoginPanel loginPanel;
	
	/**
	 * mainMenuPanel - panel chứa danh sách các thao tác ATM
	 */
	private MainMenuPanel mainMenuPanel;
	
	/**
	 * withdrawPanel - panel chứa danh sách các mức tiền rút	
	 */
	private WithdrawPanel withdrawPanel;
	
	/**
	 * viewBalancePanel - panel của số dư tài khoản 
	 */
	private ViewBalancePanel viewBalancePanel;

	public ScreenPanel(ATMWindow atm) {
		loginPanel = new LoginPanel(atm);
		mainMenuPanel = new MainMenuPanel(atm);
		withdrawPanel = new WithdrawPanel(atm);
		viewBalancePanel = new ViewBalancePanel(atm);
		
		initComponents();
	}
	
	private void initComponents() {
		setLayout(new CardLayout());
		
		add(loginPanel, LOGIN_MENU);
		add(mainMenuPanel, MAIN_MENU);
		add(withdrawPanel, WITHDRAW_MENU);
		add(viewBalancePanel, VIEW_BALANCE);
	}
	
	public void displayPanel(String name) {
		CardLayout layout = (CardLayout) getLayout();
		layout.show(this, name);
	}
}