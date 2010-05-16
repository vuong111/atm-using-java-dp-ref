package atm.screen;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import atm.utils.ATMUtils;

public class ChangePINScreen extends Screen {
	public static final String ENTER_PIN = "Nhập mã PIN: ";
	public static final String CONFIRM_PIN = "Xác nhận mã PIN: ";
	
	private JLabel pinLbl;
	private JTextField pinFld;
	
	@Override
	protected void configBackgroundImage() {
		bgImage = ATMUtils.createImageIcon(this.getClass(), "images/changePIN.png", "Change Pin").getImage();	
	}
	
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
	
	/** display PIN code **/
	public void display(final String msg) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				pinFld.setText(msg);		
			}
		});
	}
	
	/** clear PIN display **/	
	public void clearDisplay() {
		display("");
	}
	
	/** show PIN request label **/
	public void showMessage(final String msg) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				pinLbl.setText(msg);
			}
		});		
	}

}
