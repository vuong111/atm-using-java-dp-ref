package atm.gui.screen;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import atm.gui.ATMWindow;

public class WithdrawPanel extends JPanel implements ActionListener {
	private static final String MONEY_AMOUNT1 = "100000";
	private static final String MONEY_AMOUNT2 = "500000";
	private static final String MONEY_AMOUNT3 = "1000000";
	private static final String MONEY_AMOUNT4 = "2000000";
	private static final String SWITCH_TO_PANEL1 = "Switch to panel 1";
	private static final String EXIT = "Thoát";
	
	private static final String WITHDRAW = "Rút tiền";
	private static final String SWITCH_TO_PANEL2 = "Switch to panel 2";
	
	private JPanel withdrawPanel1;
	private JPanel withdrawPanel2;

//	private static ATMWindow atm = ATMWindow.getInstance();
	private ATMWindow atm;
	
	public WithdrawPanel() {
		super(new CardLayout());
		initComponents();
	}
	
	public WithdrawPanel(ATMWindow atm) {
		this();
		this.atm = atm;
	}
	
	private void initComponents() {
		/**
		 * withdrawPanel - panel chứa danh sách các mức tiền rút	
		 */
		withdrawPanel1 = new JPanel();
		withdrawPanel1.setLayout(new GridLayout(0, 3));
		
		for (int i = 0; i < 21; i++) {
			if (i == 0) {
				JButton moneyBtn1 = new JButton("100.000");
				moneyBtn1.setActionCommand(MONEY_AMOUNT1);
				moneyBtn1.addActionListener(this);
				withdrawPanel1.add(moneyBtn1);
			}
			else if (i == 2) {
				JButton moneyBtn2 = new JButton("500.000");
				moneyBtn2.setActionCommand(MONEY_AMOUNT2);
				moneyBtn2.addActionListener(this);
				withdrawPanel1.add(moneyBtn2);
			}
			else if (i == 9) {
				JButton moneyBtn3 = new JButton("1.000.000");
				moneyBtn3.setActionCommand(MONEY_AMOUNT3);
				moneyBtn3.addActionListener(this);
				withdrawPanel1.add(moneyBtn3);
			}
			else if (i == 11) {
				JButton moneyBtn4 = new JButton("2.000.000");
				moneyBtn4.setActionCommand(MONEY_AMOUNT4);
				moneyBtn4.addActionListener(this);
				withdrawPanel1.add(moneyBtn4);
			}
			else if (i == 18) {
				JButton otherAmountBtn = new JButton("Số khác");
				otherAmountBtn.setActionCommand(SWITCH_TO_PANEL2);
				otherAmountBtn.addActionListener(this);
				withdrawPanel1.add(otherAmountBtn);
			}				
			else if (i == 20) {
				JButton exitBtn = new JButton("Thoát");
				exitBtn.setActionCommand(EXIT);
				exitBtn.addActionListener(this);
				withdrawPanel1.add(exitBtn);
			}
			else
				withdrawPanel1.add(new JLabel(""));
		}
		withdrawPanel1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		/**
		 * withdrawPanel2 - panel lựa chọn mức tiền rút khác
		 */
		withdrawPanel2 = new JPanel(new GridBagLayout());
		GridBagConstraints withdrawConstraints = new GridBagConstraints();
		withdrawConstraints.fill = GridBagConstraints.HORIZONTAL;
		
		withdrawConstraints.gridx = 0;
		withdrawConstraints.gridy = 0;
		withdrawPanel2.add(new JLabel("Nhập số tiền muốn rút:"), withdrawConstraints);
		
		withdrawConstraints.gridx = 1;
		withdrawConstraints.gridy = 0;
		withdrawPanel2.add(new JTextField(11), withdrawConstraints);
		
		withdrawConstraints.gridx = 2;
		withdrawConstraints.gridy = 0;
		JButton withdrawBtn = new JButton(WITHDRAW);
		withdrawBtn.addActionListener(this);
		withdrawPanel2.add(withdrawBtn, withdrawConstraints);
		
		withdrawConstraints.gridx = 0;
		withdrawConstraints.gridy = 1;
		withdrawConstraints.gridwidth = 2;
		withdrawPanel2.add(new JLabel("(Số tiền phải là bội của 50.000)"), withdrawConstraints);
		
		withdrawConstraints.gridx = 2;
		withdrawConstraints.gridy = 1;
		withdrawConstraints.gridwidth = 1;
		JButton backBtn = new JButton("Quay lại");
		backBtn.setActionCommand(SWITCH_TO_PANEL1);
		backBtn.addActionListener(this);
		withdrawPanel2.add(backBtn, withdrawConstraints);
		
		//add components
		add(withdrawPanel1, SWITCH_TO_PANEL1);
		add(withdrawPanel2, SWITCH_TO_PANEL2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if (command.equals(EXIT)) {
			atm.getScreenPanel().displayPanel(ScreenPanel.MAIN_MENU);
			
		}
		else if (command.equals(SWITCH_TO_PANEL1) || command.equals(SWITCH_TO_PANEL2)) {
			switchToPanel(command);
		}
		else if (command.equals(WITHDRAW)) {
			
		}
		else {
			
		}
		System.out.println(command);
	}
	
	private void switchToPanel(String name) {
		CardLayout layout = (CardLayout) this.getLayout();
		layout.show(this, name);
	}
}
