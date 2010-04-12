package atm.gui.screen;   

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JTextField;

import atm.utils.ATMUtils;

public class LoginScreen extends JPanel {
	private JTextField accountNumberFld = new JTextField(11);
	private JTextField pinFld = new JTextField(11);
	
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
		//accountNumberFld.setEditable(false);
		
		//pin field
		add(pinFld);
		pinFld.setBackground(Color.green);
		pinFld.setBounds(165, 165, 140, 26);
		pinFld.setForeground(Color.red);
		//pinFld.setEditable(false);
	}
	
	public void updateAccountNumberField(String s) {
		if (s == null || s.equals(""))
			accountNumberFld.setText("");
		else
			accountNumberFld.setText(accountNumberFld.getText() + s);
	}
	
	public void updatePINField(String s) {
		if (s == null || s.equals(""))
			pinFld.setText("");
		else
			pinFld.setText(pinFld.getText() + s);
	}	
	
	@Override
	public void paintComponent(Graphics g) {
	    g.drawImage(bgImage, 0, 0, null);
	}

}