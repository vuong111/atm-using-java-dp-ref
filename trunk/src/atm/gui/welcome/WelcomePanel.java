package atm.gui.welcome;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import atm.gui.ATMWindow;

/**
 * I - welcomePanel
 */
public class WelcomePanel extends JPanel {
	public static final String LOGIN_MENU = "Login Menu";
	public static final String MAIN_MENU = "Main Menu";
	public static final String WITHDRAW_MENU = "Withdraw Menu";
	public static final String VIEW_BALANCE = "View Balance";
	
	private JComboBox cb;
		
//	private static ATMWindow atm = ATMWindow.getInstance();
	private ATMWindow atm;
	
	public WelcomePanel() {		
		initComponents();
	}
	
	public WelcomePanel(ATMWindow atm) {
		this();
		this.atm = atm;
	}
	
	private void initComponents() {

		add(new JLabel("Welcome to my ATM"));
		
		String[] cbItems = new String[] {LOGIN_MENU, MAIN_MENU, WITHDRAW_MENU, VIEW_BALANCE};
		setCb(new JComboBox(cbItems));
		getCb().addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				atm.getScreenPanel().displayPanel((String) e.getItem());
			}
		});
		
		add(getCb());
		setBackground(new Color(0, 204, 51));
	}
	
	public JComboBox getCb() {
		return cb;
	}

	public void setCb(JComboBox cb) {
		this.cb = cb;
	}
}