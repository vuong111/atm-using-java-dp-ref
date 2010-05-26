package atm.screen;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BalanceInquiryScreen extends ScreenType {
	
	private JTextField accountNumberFld;
	private JTextField fullNameFld;
	private JTextField balanceFld;

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
		accountNumberFld = new JTextField();
		fullNameFld = new JTextField();
		balanceFld = new JTextField();
		
		add(accountNumberFld);
		accountNumberFld.setBackground(Color.green);
		accountNumberFld.setBounds(190, 107, 140, 26);
		accountNumberFld.setForeground(Color.red);
		accountNumberFld.setEditable(false);

		add(fullNameFld);
		fullNameFld.setBackground(Color.green);
		fullNameFld.setBounds(190, 142, 140, 26);
		fullNameFld.setForeground(Color.red);
		fullNameFld.setEditable(false);
		
		add(balanceFld);
		balanceFld.setBackground(Color.green);
		balanceFld.setBounds(190, 177, 140, 26);
		balanceFld.setForeground(Color.red);
		balanceFld.setEditable(false);

	}

	private void printAccountNumber(String msg) {
		accountNumberFld.setText(msg);
	}
	
	private void printAccountName(String msg) {
		fullNameFld.setText(msg);
	}
	
	private void printAccountBalance(String msg) {
		balanceFld.setText(msg);
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