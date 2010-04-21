package atm.gui.screen;

import java.awt.CardLayout;
import java.awt.Component;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 * II - screenPanel
 */
public class Screen extends JPanel {
	
	public static final String WELCOME = "Welcome";
	public static final String LOGIN_MENU = "Login";
	public static final String MAIN_MENU = "Main Menu";
	public static final String WITHDRAW_MENU = "Withdraw";
	public static final String VIEW_BALANCE = "View Balance";
	public static final String TRANSFER = "Transfer";
	public static final String CHANGE_PIN = "Change Pin";
	
	/** map - store child components **/
	HashMap<String, Component> map = new HashMap<String, Component>();
	
	/** welcomeScreen - screen welcome **/
	private WelcomeScreen welcomeScreen = new WelcomeScreen();
	
	/** loginScreen - screen cho màn hình đăng nhập **/
	private LoginScreen loginScreen = new LoginScreen();
	
	/** mainMenuScreen - screen chứa danh sách các thao tác ATM **/
	private MainMenuScreen mainMenuScreen = new MainMenuScreen();
	
	/** withdrawScreen - screen chứa danh sách các mức tiền rút	**/
	private WithdrawScreen withdrawScreen = new WithdrawScreen();
	
	/** viewBalanceScreen - screen của số dư tài khoản **/
	private BalanceInquiryScreen viewBalanceScreen = new BalanceInquiryScreen();
	
	/** transferScreen - screen chuyển khoản **/
	private TransferScreen transferScreen = new TransferScreen();
	
	/** changePINScreen - screen đổi số PIN **/
	private ChangePINScreen changePINScreen = new ChangePINScreen();
	
	/** constructor **/
	public Screen() {
		initComponents();
	}
	
	private void initComponents() {
		setLayout(new CardLayout());
		
		add(welcomeScreen, WELCOME);
		add(loginScreen, LOGIN_MENU);
		add(mainMenuScreen, MAIN_MENU);
		add(withdrawScreen, WITHDRAW_MENU);
		add(viewBalanceScreen, VIEW_BALANCE);
		add(transferScreen, TRANSFER);
		add(changePINScreen, CHANGE_PIN);
	}
	
	/** Hiển thị screen **/
	public void show(String name) {
		CardLayout layout = (CardLayout) getLayout();
		layout.show(this, name);
	}
	
	/** getters **/
	public LoginScreen getLoginScreen() {
		return loginScreen;
	}

	public MainMenuScreen getMainMenuScreen() {
		return mainMenuScreen;
	}

	public WithdrawScreen getWithdrawScreen() {
		return withdrawScreen;
	}

	public BalanceInquiryScreen getViewBalanceScreen() {
		return viewBalanceScreen;
	}

	public TransferScreen getTransferScreen() {
		return transferScreen;
	}

	public ChangePINScreen getChangePINScreen() {
		return changePINScreen;
	}
	
	//
	
	@Override
	public void add(Component comp, Object constraints) {
		map.put((String) constraints, comp);
		super.add(comp, constraints);		
	}
	
	public Component getComponent(String name) {
		return map.get(name);
	}
	
}