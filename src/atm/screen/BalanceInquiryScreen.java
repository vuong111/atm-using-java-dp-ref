package atm.screen;

import java.awt.Color;
import javax.swing.JTextField;
import atm.utils.ATMUtils;

public class BalanceInquiryScreen extends Screen {
	private JTextField accountNumberFld;
	private JTextField fullNameFld;
	private JTextField balanceFld;	

	@Override
	protected void configBackgroundImage() {
		bgImage = ATMUtils.createImageIcon(this.getClass(), "images/viewbalance.png", "View balance").getImage();
		
	}
	
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
	
	/*
	 * displayMessage(int index, String msg) 
	 * => Replace Parameter with Explicit Methods
	 */
	
	/** display account number **/
	public void displayMessage1(String msg) {
		accountNumberFld.setText(msg);
	}
	
	/** display account's full name **/
	public void displayMessage2(String msg) {
		fullNameFld.setText(msg);
	}

	/** display account balance **/
	public void displayMessage3(String msg) {
		balanceFld.setText(msg);
	}

}