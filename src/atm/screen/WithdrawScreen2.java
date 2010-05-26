package atm.screen;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class WithdrawScreen2 extends ScreenType {
	private JTextField amountField;

	@Override
	public int getTypeCode() {
		return ScreenType.WITHDRAW2_TYPE;
	}
	
	@Override
	protected String getImageName() {
		return "withdraw2.png";
	};
	
	@Override
	protected String getImageDescription() {
		return "Withdraw 2";
	};
	
	@Override
	public void addComponents() {	
		amountField = new JTextField(11);	
		
		add(amountField);
		amountField.setBackground(Color.green);
		amountField.setBounds(190, 131, 140, 26);
		amountField.setForeground(Color.red);
		amountField.setEditable(false);
	}

	@Override
	public void printMessage(String msg, int pos) {
		//print nothing..
	}
	
	@Override
	public void displayInput(final String msg) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				amountField.setText(msg);
			}
		});
	}
}
