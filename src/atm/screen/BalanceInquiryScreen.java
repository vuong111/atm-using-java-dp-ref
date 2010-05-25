package atm.screen;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import atm.utils.ATMUtils;

public class BalanceInquiryScreen extends ScreenType {
	private static final String IMAGE_NAME = "viewbalance.png";
	private static final String IMAGE_DESCRIPTION = "View Balance";
	
	private JTextField accountNumberFld;
	private JTextField fullNameFld;
	private JTextField balanceFld;

	@Override
	public int getTypeCode() {
		return ScreenType.VIEW_TYPE;
	}
	
	@Override
	protected String getImageName() {
		return IMAGE_NAME;
	};
	
	@Override
	protected String getImageDescription() {
		return IMAGE_DESCRIPTION;
	};
	
	@Override
	public void addComponents() {
		accountNumberFld = new JTextField();
		fullNameFld = new JTextField();
		balanceFld = new JTextField();
		
		add(accountNumberFld);
		accountNumberFld.setBackground(Color.green);
		accountNumberFld.setBounds(165, 105, 140, 26);
		accountNumberFld.setForeground(Color.red);
		accountNumberFld.setEditable(false);

		add(fullNameFld);
		fullNameFld.setBackground(Color.green);
		fullNameFld.setBounds(165, 140, 140, 26);
		fullNameFld.setForeground(Color.red);
		fullNameFld.setEditable(false);
		
		add(balanceFld);
		balanceFld.setBackground(Color.green);
		balanceFld.setBounds(165, 175, 140, 26);
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