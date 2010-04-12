package atm.gui.welcome;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import atm.gui.ATMWindow;
import atm.gui.screen.Screen;

/**
 * I - welcomePanel
 */
public class WelcomePanel extends JPanel {
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
		
		String[] cbItems = new String[] {Screen.LOGIN_MENU, 
										Screen.MAIN_MENU, 
										Screen.WITHDRAW_MENU, 
										Screen.VIEW_BALANCE,
										Screen.TRANSFER,
										Screen.CHANGE_PIN};
		setCb(new JComboBox(cbItems));
		getCb().addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				atm.getScreen().displayScreen((String) e.getItem());
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