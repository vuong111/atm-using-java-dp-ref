package atm.screen;   

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LoginScreen extends ScreenType {
	
	private JTextField pinFld;
	
	@Override
	public int getTypeCode() {
		return ScreenType.LOGIN_TYPE;
	}
	
	@Override
	public String getImageName() {
		return "login.png";
	};
	
	@Override
	public String getImageDescription() {
		return "Login";
	};
	
	@Override
	public void addComponents() {
		pinFld = new JTextField(11);
		
		add(pinFld);
		pinFld.setBackground(Color.green);
		pinFld.setBounds(190, 165, 140, 26);
		pinFld.setForeground(Color.red);
		pinFld.setEditable(false);
		
	}
	
	private void displayPINInput(String msg) {
		pinFld.setText(msg);
	}
	
	@Override
	public void printMessage(final String msg, final int pos) {
		//print nothing..
	}
	
	@Override
	public void displayInput(final String msg) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				displayPINInput(msg);
			}
		});	
	}
}