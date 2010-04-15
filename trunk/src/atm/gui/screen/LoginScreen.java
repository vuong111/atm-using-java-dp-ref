package atm.gui.screen;   

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JTextField;

import atm.utils.ATMUtils;

public class LoginScreen extends JPanel {
	private JTextField accountNumberFld = new JTextField("12345", 11);
	private JTextField pinFld = new JTextField("54321", 11);
	
	private Image bgImage;
	
	public LoginScreen() {	
		bgImage = ATMUtils.createImageIcon(this.getClass(), "images/login.png", "Login").getImage();
		Dimension size = new Dimension(bgImage.getWidth(null), bgImage.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	    
		initComponents();
	}
	
	private void initComponents() {	    
		//account number field
		add(accountNumberFld);
		accountNumberFld.setBackground(Color.green);
		accountNumberFld.setBounds(165, 131, 140, 26);
		accountNumberFld.setForeground(Color.red);
		accountNumberFld.setEditable(false);
		
		//pin field
		add(pinFld);
		pinFld.setBackground(Color.green);
		pinFld.setBounds(165, 165, 140, 26);
		pinFld.setForeground(Color.red);
		pinFld.setEditable(false);
	}
	
	/** display account number **/
	public void showMessage1(String s) {
		accountNumberFld.setText(s);
	}
	
	/** display PIN code **/
	public void showMessage2(String s) {
		pinFld.setText(s);
	}
	
	/** clear all fields **/	
	public void clearAllMessages() {
		showMessage1("");
		showMessage2("");
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    g.drawImage(bgImage, 0, 0, null);
	}

}