package atm.screen;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

class TransferScreen2 extends ScreenType {
	
	private JLabel accountNumberLbl;
	private JLabel accountNameLbl;
	private JTextField amountFld;	

	@Override
	public int getTypeCode() {
		return ScreenType.TRANSFER2_TYPE;
	}
	
	@Override
	protected String getImageName() {
		return "transfer2.png";
	};
	
	@Override
	protected String getImageDescription() {
		return "Transfer 2";
	};
	
	@Override
	public void addComponents() {
		accountNumberLbl = new JLabel();
		accountNameLbl = new JLabel();
		amountFld = new JTextField(11);
		
		//transfer account number
	    add(accountNumberLbl);
	    accountNumberLbl.setBackground(Color.green);
	    accountNumberLbl.setBounds(190, 112, 140, 26);
	    accountNumberLbl.setForeground(Color.red);
		
		//transfer account full name
		add(accountNameLbl);
		accountNameLbl.setBackground(Color.green);
		accountNameLbl.setBounds(190, 142, 140, 26);
		accountNameLbl.setForeground(Color.red);
		
		//transfer account money
		add(amountFld);
		amountFld.setBackground(Color.green);
		amountFld.setBounds(190, 220, 140, 26);
		amountFld.setForeground(Color.red);
		amountFld.setEditable(false);		
	}
	
	private void printAccountNumber(String msg) {
		accountNumberLbl.setText(msg);
	}
	
	private void printAccountName(String msg) {
		accountNameLbl.setText(msg);
	}
	
	private void displayTransferMoneyInput(String msg) {
		amountFld.setText(msg);
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