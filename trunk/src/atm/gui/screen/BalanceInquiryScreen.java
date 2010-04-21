package atm.gui.screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import atm.utils.ATMUtils;

public class BalanceInquiryScreen extends JPanel {
	private JTextField accountNumberFld = new JTextField("123456");
	private JTextField fullNameFld = new JTextField("Vietcombank");
	private JTextField balanceFld = new JTextField("15.000.000 VND");
	private Image bgImage;
	
	public BalanceInquiryScreen() {	    
	    initComponents();
	}
	
	private void initComponents() {
		bgImage = ATMUtils.createImageIcon(this.getClass(), "images/viewbalance.png", "View balance").getImage();
		Dimension size = new Dimension(bgImage.getWidth(null), bgImage.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	    
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
	
	@Override
	public void paintComponent(Graphics g) {
	    g.drawImage(bgImage, 0, 0, null);
	}
}