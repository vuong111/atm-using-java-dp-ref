package atm.screen;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import atm.utils.ATMUtils;

public class BalanceInquiryScreen extends Screen {
	private static final String IMAGE_NAME = "viewbalance.png";
	private static final String IMAGE_DESCRIPTION = "View Balance";
		
	private JTextField accountNumberFld;
	private JTextField fullNameFld;
	private JTextField balanceFld;

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

	/** display account number **/
	@Override
	public void displayMessage1(final String msg) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				accountNumberFld.setText(msg);
			}
		});		
	}
	
	/** display account's full name **/
	@Override
	public void displayMessage2(final String msg) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				fullNameFld.setText(msg);
			}
		});			
	}

	/** display account balance **/
	@Override
	public void displayMessage3(final String msg) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				balanceFld.setText(msg);
			}
		});		
	}
	
	@Override
	public void clearDisplay() {
		//clear nothing..
	}
}