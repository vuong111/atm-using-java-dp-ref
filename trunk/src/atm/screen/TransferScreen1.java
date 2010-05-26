package atm.screen;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TransferScreen1 extends ScreenType {
	
	private JTextField inputField;

	@Override
	public int getTypeCode() {
		return ScreenType.TRANSFER1_TYPE;
	}
	
	@Override
	protected String getImageName() {
		return "transfer1.png";
	};
	
	@Override
	protected String getImageDescription() {
		return "Transfer 1";
	};
	
	@Override
	public void addComponents() {	
		inputField = new JTextField(11);	
		
		add(inputField);
		inputField.setBackground(Color.green);
		inputField.setBounds(190, 131, 140, 26);
		inputField.setForeground(Color.red);
		inputField.setEditable(false);
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
				inputField.setText(msg);
			}
		});
	}
}
