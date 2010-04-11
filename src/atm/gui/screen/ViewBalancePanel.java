package atm.gui.screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import atm.gui.ATMWindow;

public class ViewBalancePanel extends JPanel implements ActionListener {
	private long amount = 15000000;
	private JButton exitBtn = new JButton("Thoát");
	
//	private static ATMWindow atm = ATMWindow.getInstance();
	private ATMWindow atm;
	
	public ViewBalancePanel() {
		super(new GridBagLayout());
		initComponents();
	}
	
	public ViewBalancePanel(ATMWindow atm) {
		this();
		this.atm = atm;
	}
	
	private void initComponents() {

		GridBagConstraints viewConstraints = new GridBagConstraints();
		//withdrawConstraints.fill = GridBagConstraints.HORIZONTAL;
		
		viewConstraints.gridx = 0;
		viewConstraints.gridy = 0;		
		add(new JLabel("Số tiền tài khoản của bạn là: " + amount), viewConstraints);
		
		viewConstraints.gridx = 0;
		viewConstraints.gridy = 1;
		add(exitBtn, viewConstraints);
		exitBtn.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		atm.getScreenPanel().displayPanel(ScreenPanel.MAIN_MENU);
		System.out.println(e.getActionCommand());
	}
}