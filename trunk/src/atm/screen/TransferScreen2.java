package atm.screen;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

class TransferScreen2 extends ScreenType {
	private static final String IMAGE_NAME = "transfer2.png";
	private static final String IMAGE_DESCRIPTION = "Transfer2";
	
	private JLabel accountNumberLbl;
	private JLabel accountNameLbl;
	private JTextField moneyFld;	

	@Override
	public int getTypeCode() {
		return ScreenType.TRANSFER2_TYPE;
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
		accountNumberLbl = new JLabel();
		accountNameLbl = new JLabel();
		moneyFld = new JTextField(11);
		
		//transfer account number
	    add(accountNumberLbl);
	    accountNumberLbl.setBackground(Color.green);
	    accountNumberLbl.setBounds(165, 105, 140, 26);
	    accountNumberLbl.setForeground(Color.red);
		
		//transfer account full name
		add(accountNameLbl);
		accountNameLbl.setBackground(Color.green);
		accountNameLbl.setBounds(165, 135, 140, 26);
		accountNameLbl.setForeground(Color.red);
		
		//transfer account money
		add(moneyFld);
		moneyFld.setBackground(Color.green);
		moneyFld.setBounds(165, 175, 140, 26);
		moneyFld.setForeground(Color.red);
		moneyFld.setEditable(false);		
	}
	
	private void printAccountNumber(String msg) {
		accountNumberLbl.setText(msg);
	}
	
	private void printAccountName(String msg) {
		accountNameLbl.setText(msg);
	}
	
	private void displayTransferMoneyInput(String msg) {
		moneyFld.setText(msg);
	}
	
	@Override
	public void printMessage(final String msg, final int pos) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				switch(pos) {
				case 1:
					printAccountNumber(msg);
					break;
				case 2:
					printAccountName(msg);
					break;	
				}
			}
		});
	}
	
	@Override
	public void displayInput(final String msg) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				displayTransferMoneyInput(msg);
			}
		});
	}
}