package atm.gui.screen;

import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * II - screenPanel
 */
public class Screen extends JPanel {

	public static final String LOGIN_MENU = "Login";
	public static final String MAIN_MENU = "Main Menu";
	public static final String WITHDRAW_MENU = "Withdraw";
	public static final String VIEW_BALANCE = "View Balance";
	public static final String TRANSFER = "Transfer";
	public static final String CHANGE_PIN = "Change Pin";
	
	/** loginScreen - screen cho màn hình đăng nhập **/
	private LoginScreen loginScreen = new LoginScreen();
	
	/** mainMenuScreen - screen chứa danh sách các thao tác ATM **/
	private MainMenuScreen mainMenuScreen = new MainMenuScreen();;
	
	/** withdrawScreen - screen chứa danh sách các mức tiền rút	**/
	private WithdrawScreen withdrawScreen = new WithdrawScreen();
	
	/** viewBalanceScreen - screen của số dư tài khoản **/
	private ViewBalanceScreen viewBalanceScreen = new ViewBalanceScreen();
	
	/** transferScreen - screen chuyển khoản **/
	private TransferScreen transferScreen = new TransferScreen();
	
	/** changePINScreen - screen đổi số PIN **/
	private ChangePINScreen changePINScreen = new ChangePINScreen();
	
	/** constructor **/
	public Screen() {
		super(new CardLayout());
		initComponents();
	}
	
	private void initComponents() {
		add(loginScreen, LOGIN_MENU);
		add(mainMenuScreen, MAIN_MENU);
		add(withdrawScreen, WITHDRAW_MENU);
		add(viewBalanceScreen, VIEW_BALANCE);
		add(transferScreen, TRANSFER);
		add(changePINScreen, CHANGE_PIN);
	}
	
	/** Hiển thị screen **/
	public void displayScreen(String name) {
		CardLayout layout = (CardLayout) getLayout();
		layout.show(this, name);
	}
	
	//
	public JPanel getScreen(String name) {
		displayScreen(name);
		return (JPanel) getComponent(0);
	}	
	
}