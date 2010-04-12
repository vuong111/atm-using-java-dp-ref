package atm.gui.removed;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import atm.gui.ATMWindow;

public class LoginPanel extends JPanel implements ActionListener {
	private static final String LOGIN = "Đăng nhập";
	private static final String CANCEL = "Hủy";
	
	private JTextField accountNumberFld = new JTextField(11);
	private JTextField passwordFld = new JTextField(11);
	
	private JButton loginBtn = new JButton(LOGIN);
	private JButton cancelBtn = new JButton(CANCEL);
	
//	private static ATMWindow atm = ATMWindow.getInstance();
	private ATMWindow atm;
	
	public LoginPanel() {
		super(new GridBagLayout());
		initComponents();
	}
	
	public LoginPanel(ATMWindow atm) {
		this();
		this.atm = atm;
	}
	
	private void initComponents() {
		GridBagConstraints loginConstraints = new GridBagConstraints();
		loginConstraints.fill = GridBagConstraints.HORIZONTAL;
		
		loginConstraints.gridx = 0;
		loginConstraints.gridy = 0;
		add(new JLabel("Nhập mã số tài khoản:"), loginConstraints);
		
		loginConstraints.gridx = 1;
		loginConstraints.gridy = 0;
		add(accountNumberFld, loginConstraints);
		
		loginConstraints.gridx = 0;
		loginConstraints.gridy = 1;
		add(new JLabel("Nhập mã PIN:"), loginConstraints);
		
		loginConstraints.gridx = 1;
		loginConstraints.gridy = 1;
		add(passwordFld, loginConstraints);
		
		loginConstraints.gridx = 0;
		loginConstraints.gridy = 2;
		add(cancelBtn, loginConstraints);
		cancelBtn.addActionListener(this);
		
		loginConstraints.gridx = 1;
		loginConstraints.gridy = 2;
		add(loginBtn, loginConstraints);
		loginBtn.addActionListener(this);
		//loginPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if (command.equals(LOGIN)) {
			//atm.getScreenPanel().displayPanel(ScreenPanel.MAIN_MENU);
			
		}
		System.out.println(command);
	}
}