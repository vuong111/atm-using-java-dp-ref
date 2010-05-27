package atm.screen;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BalanceInquiryScreen extends ScreenType {
	
	private JLabel accountNumberLbl;
	private JLabel accountNameLbl;
	private JLabel accountBalanceLbl;

	@Override
	public int getTypeCode() {
		return ScreenType.VIEW_TYPE;
	}
	
	@Override
	protected String getImageName() {
		return "viewbalance.png";
	};
	
	@Override
	protected String getImageDescription() {
		return "View Balance";
	};
	
	@Override
	public void addComponents() {
		accountNumberLbl = new JLabel();
		accountNameLbl = new JLabel();
		accountBalanceLbl = new JLabel();
		
		add(accountNumberLbl);
		accountNumberLbl.setBackground(Color.green);
		accountNumberLbl.setBounds(190, 114, 200, 26);
		accountNumberLbl.setForeground(Color.red);

		add(accountNameLbl);
		accountNameLbl.setBackground(Color.green);
		accountNameLbl.setBounds(190, 147, 200, 26);
		accountNameLbl.setForeground(Color.red);
		
		add(accountBalanceLbl);
		accountBalanceLbl.setBackground(Color.green);
		accountBalanceLbl.setBounds(190, 182, 200, 26);
		accountBalanceLbl.setForeground(Color.red);

	}

	private void printAccountNumber(String msg) {
		accountNumberLbl.setText(msg);
	}
	
	private void printAccountName(String msg) {
		accountNameLbl.setText(msg);
	}
	
	private void printAccountBalance(String msg) {
		accountBalanceLbl.setText(msg);
	}
	
	@Override
	public void printMessage(final String msg, final int pos) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				switch (pos) {
				case 1:
					printAccountNumber(msg);
					break;
				case 2:
					printAccountName(msg);
					break;
				case 3:
					printAccountBalance(msg);
					break;
				}
			}
		});			
	}
	
	@Override
	public void displayInput(final String msg) {
		//display nothing..
	}
}