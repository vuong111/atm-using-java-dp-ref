package atm.screen;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TransferScreen1 extends ScreenType {
	private static final String IMAGE_NAME = "transfer1.png";
	private static final String IMAGE_DESCRIPTION = "Transfer1";
	
	private JTextField accountFld;

	@Override
	public int getTypeCode() {
		return ScreenType.TRANSFER1_TYPE;
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
		accountFld = new JTextField(11);	
		
		add(accountFld);
		accountFld.setBackground(Color.green);
		accountFld.setBounds(165, 131, 140, 26);
		accountFld.setForeground(Color.red);
		accountFld.setEditable(false);
	}
	
	private void displayAccountNumberInput(String msg) {
		accountFld.setText(msg);
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
				displayAccountNumberInput(msg);
			}
		});
	}
}
