package atm.screen;   

import java.awt.Color;

import javax.swing.JTextField;
import atm.utils.ATMUtils;

public class LoginScreen extends Screen {
	private JTextField pinFld;
	
	@Override
	protected void configBackgroundImage() {
		bgImage = ATMUtils.createImageIcon(this.getClass(), "images/login.png", "Login").getImage();
		
	}
	
	@Override
	public void addComponents() {
		pinFld = new JTextField(11);
		
		add(pinFld);
		pinFld.setBackground(Color.green);
		pinFld.setBounds(165, 165, 140, 26);
		pinFld.setForeground(Color.red);
		pinFld.setEditable(false);
		
	}
	
	/** display PIN code **/
	public void display(String s) {
		pinFld.setText(s);
	}
	
	/** clear display **/	
	public void clearDisplay() {
		display("");
	}
}