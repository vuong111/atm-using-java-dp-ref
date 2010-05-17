package atm.screen;   

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import atm.utils.ATMUtils;

public class LoginScreen extends Screen {
	private static final String IMAGE_NAME = "login.png";
	private static final String IMAGE_DESCRIPTION = "Login";
	
	private JTextField pinFld;
	
	@Override
	public String getImageName() {
		return IMAGE_NAME;
	};
	
	@Override
	public String getImageDescription() {
		return IMAGE_DESCRIPTION;
	};
	
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
	@Override
	public void displayMessage1(final String msg) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				pinFld.setText(msg);
			}
		});
	}
	
	@Override
	public void displayMessage2(String msg) {
		//display nothing..
	}
	
	@Override
	public void displayMessage3(String msg) {
		//display nothing..
	}
	
	/** clear display **/
	@Override
	public void clearDisplay() {
		displayMessage1("");
	}
}