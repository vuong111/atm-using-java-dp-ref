package atm.screen;

import java.awt.CardLayout;
import java.awt.Component;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 * II - screenPanel
 */
public class ScreenController extends JPanel {
	
	/** screenMap - stores screens **/
	private HashMap<String, Screen> screenMap = new HashMap<String, Screen>();
	
	/** currentScreen **/
	private Screen currentScreen;
	
	/** constructor **/
	public ScreenController() {
		initComponents();
	}
	
	private void initComponents() {
		setLayout(new CardLayout());
		
		add(Screen.getWelcomeScreen(), Screen.WELCOME);
		add(Screen.getLoginScreen(), Screen.LOGIN_MENU);
		add(Screen.getMainMenuScreen(), Screen.MAIN_MENU);
		add(Screen.getWithdrawScreen(), Screen.WITHDRAW_MENU);
		add(Screen.getBalanceInquiryScreen(), Screen.VIEW_BALANCE);
		add(Screen.getTransferScreen1(), Screen.TRANSFER1);
		add(Screen.getTransferScreen2(), Screen.TRANSFER2);
		add(Screen.getChangePINScreen(), Screen.CHANGE_PIN);
	}
	
	public void setCurrentScreen(Screen screen) {
		currentScreen = screen;
	}
	
	public Screen getCurrentScreen() {
		return currentScreen;
	}
	
	/** Hiển thị screen **/
	public void showScreen(String name) {		
		CardLayout layout = (CardLayout) getLayout();
		layout.show(this, name);
		
		//store current screen
		setCurrentScreen(screenMap.get(name));
	}
	
	public void printMessage(String msg, int posIndex) {
		getCurrentScreen().printMessage(msg, posIndex);
	}
	
	public void displayInput(String msg) {
		getCurrentScreen().displayInput(msg);
	}
	
	public void clearDisplay() {
		getCurrentScreen().clearDisplay();
	}
	
	@Override
	public void add(Component comp, Object constraints) {
		screenMap.put((String) constraints, (Screen) comp);
		super.add(comp, constraints);
	}
}