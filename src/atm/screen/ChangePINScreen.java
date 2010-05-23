package atm.screen;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import atm.utils.ATMUtils;

public class ChangePINScreen extends ScreenType {
	private static final String IMAGE_NAME = "changePIN.png";
	private static final String IMAGE_DESCRIPTION = "Change PIN";
	
	public static final String ENTER_PIN = "Nhập mã PIN: ";
	public static final String CONFIRM_PIN = "Xác nhận mã PIN: ";
	
	private JLabel pinLbl;
	private JTextField pinFld;
	
	@Override
	public int getTypeCode() {
		return ScreenType.CHANGE_PIN_TYPE;
	}
	
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
		pinLbl = new JLabel(ENTER_PIN);
		pinFld = new JTextField(11);
		
		//PIN label
		add(pinLbl);
		pinLbl.setBackground(Color.black);
		pinLbl.setBounds(40, 165, 140, 26);
		pinLbl.setForeground(Color.red);
		
		//PIN field
		add(pinFld);
		pinFld.setBackground(Color.green);
		pinFld.setBounds(165, 165, 140, 26);
		pinFld.setForeground(Color.red);
		pinFld.setEditable(false);
	}
	
	private void printChangePINLabel(String msg) {
		pinLbl.setText(msg);
	}
	
	private void displayPINInput(String msg) {
		pinFld.setText(msg);
	}
	
	@Override
	public void printMessage(final String msg, final int pos) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				printChangePINLabel(msg);
			}
		});
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
