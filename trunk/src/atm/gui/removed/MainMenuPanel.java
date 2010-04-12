package atm.gui.removed;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import atm.gui.ATMWindow;

public class MainMenuPanel extends JPanel implements ActionListener {
	private static final String VIEW_BALANCE = "Xem số dư";
	private static final String WITHDRAW = "Rút tiền";
	private static final String DEPOSIT = "Nạp tiền";
	private static final String TRANSFER = "Chuyển khoản";
	private static final String CHANGE_PIN = "Đổi mã PIN";
	private static final String EXIT = "Thoát";
	
	private JButton viewBalanceBtn = new JButton(VIEW_BALANCE);
	private JButton withdrawBtn = new JButton(WITHDRAW);
	private JButton depositBtn = new JButton(DEPOSIT);
	private JButton transferBtn = new JButton(TRANSFER);
	private JButton changePinBtn = new JButton(CHANGE_PIN);
	private JButton exitBtn = new JButton(EXIT);
	
//	private static ATMWindow atm = ATMWindow.getInstance();	
	private ATMWindow atm;
	
	public MainMenuPanel() {
		super(new GridLayout(0, 3));
		initComponents();
	}
	
	public MainMenuPanel(ATMWindow atm) {
		this();
		this.atm = atm;
	}
	
	private void initComponents() {
		
		for (int i = 0; i < 21; i++) {
			if (i == 0) {
				add(viewBalanceBtn);
				viewBalanceBtn.addActionListener(this);
			}
			else if (i == 2) {
				add(withdrawBtn);
				withdrawBtn.addActionListener(this);
			}
			else if (i == 9) {
				add(depositBtn);
				depositBtn.addActionListener(this);
			}
			else if (i == 11) {
				add(transferBtn);
				transferBtn.addActionListener(this);
			}
			else if (i == 18) {
				add(changePinBtn);
				changePinBtn.addActionListener(this);
			}
			else if (i == 20) {
				add(exitBtn);
				exitBtn.addActionListener(this);				
			}
			else
				add(new JLabel(""));
		}
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if (command.equals(VIEW_BALANCE)) {
			//atm.getScreenPanel().displayPanel(ScreenPanel.VIEW_BALANCE);
			
		}
		else if (command.equals(WITHDRAW)) {
			//atm.getScreenPanel().displayPanel(ScreenPanel.WITHDRAW_MENU);
			
		}
		else if (command.equals(DEPOSIT)) {
			
		}			
		else if (command.equals(TRANSFER)) {
			
		}
		else if (command.equals(CHANGE_PIN)) {
			
		}
		else if (command.equals(EXIT)) {
			//atm.getScreenPanel().displayPanel(ScreenPanel.LOGIN_MENU);
			
		}
		System.out.println(command);
	}
}