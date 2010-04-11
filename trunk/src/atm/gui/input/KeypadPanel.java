package atm.gui.input;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import atm.gui.ATMWindow;
import atm.gui.screen.ScreenPanel;

public class KeypadPanel extends JPanel implements ActionListener {
	
	private static final String ENTER = "Enter";
	private static final String CANCEL = "Cancel";
	private static final String CLEAR = "Clear";
	
	private JButton enterBtn = new JButton(ENTER);
	private JButton cancelBtn = new JButton(CANCEL);
	private JButton clearBtn = new JButton(CLEAR);
	
//	private static ATMWindow atm = ATMWindow.getInstance();
	private ATMWindow atm;
	
	public KeypadPanel() {
		super(new GridBagLayout());
		initComponents();
	}
	
	public KeypadPanel(ATMWindow atm) {
		this();
		this.atm = atm;
	}
	
	private void initComponents() {
		GridBagConstraints keypadConstraints = new GridBagConstraints();
		keypadConstraints.fill = GridBagConstraints.HORIZONTAL;
		
		for (int i = 0; i <= 9; i++) {
			if (i < 3) {
				keypadConstraints.gridy = 0;
				keypadConstraints.gridx = i;
			}
			else if (i < 6) {
				keypadConstraints.gridy = 1;
				keypadConstraints.gridx = i - 3;
			}
			else if (i < 9){
				keypadConstraints.gridy = 2;
				keypadConstraints.gridx = i - 6;
			}
			else {
				keypadConstraints.gridy = 3;
				keypadConstraints.gridx = i - 9;
			}
			
			JButton numberBtn = new JButton(i + "");
			numberBtn.addActionListener(this);
			add(numberBtn, keypadConstraints);
		}
		//"Clear" button
		keypadConstraints.gridx++;
		keypadConstraints.gridwidth = 2;		
		add(clearBtn, keypadConstraints);
		clearBtn.addActionListener(this);
		
		//"null" button
		keypadConstraints.gridx = 0;
		keypadConstraints.gridy = 4;
		keypadConstraints.gridwidth = 1;
		add(new JButton("x"), keypadConstraints);
		
		//"Cancel" button
		keypadConstraints.gridx = 1;
		keypadConstraints.gridy = 4;
		keypadConstraints.gridwidth = 2;
		add(cancelBtn, keypadConstraints);
		cancelBtn.addActionListener(this);
		
		//"null" button
		keypadConstraints.gridx = 0;
		keypadConstraints.gridy = 5;
		keypadConstraints.gridwidth = 1;
		add(new JButton("x"), keypadConstraints);
		
		//"Enter" button
		keypadConstraints.gridx = 1;
		keypadConstraints.gridy = 5;
		keypadConstraints.gridwidth = 2;
		add(enterBtn, keypadConstraints);
		enterBtn.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if (command.equals(ENTER)) {
			System.out.println(command);
		}
		else if (command.equals(CANCEL)) {
			atm.getScreenPanel().displayPanel(ScreenPanel.LOGIN_MENU);
			System.out.println(command);
		}
		else if (command.equals(CLEAR)) {
			
		}
		else {
			System.out.println(command);
		}
	}
}
