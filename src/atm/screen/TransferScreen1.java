package atm.screen;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TransferScreen1 extends ScreenType {
	
	private JTextField accountFld;

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
		accountFld = new JTextField(11);	
		
		add(accountFld);
		accountFld.setBackground(Color.green);
		accountFld.setBounds(190, 131, 140, 26);
		accountFld.setForeground(Color.red);
		accountFld.setEditable(false);
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
				accountFld.setText(msg);
			}
		});
	}
}
