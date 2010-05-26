package atm.screen;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ChangePINScreen1 extends ScreenType {

	private JTextField pinFld;
	
	@Override
	public int getTypeCode() {
		return ScreenType.CHANGE_PIN1_TYPE;
	}
	
	@Override
	protected String getImageName() {
		return "changePIN1.png";
	};
	
	@Override
	protected String getImageDescription() {
		return "Change PIN 1";
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
	public void printMessage(final String msg, final int pos) {}		
	
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
