package atm.input;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import atm.utils.ATMUtils;

public class CardSlot extends JPanel implements ActionListener {

	private JTextField cardNumberFld;
	private JButton cardSlot;
	
	private boolean isInserted = false;
	
	public CardSlot() {
		setLayout(new GridLayout(0, 1));
		
		cardNumberFld = new JTextField("12345");
		cardSlot = new JButton("Đẩy thẻ vào");
		cardSlot.addActionListener(this);
		
		add(cardNumberFld);
		add(cardSlot);
	}
	
	public boolean isInserted() {
		return isInserted;
	}
	
	public synchronized int getCardNumber() {
		
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return ATMUtils.parseInt(cardNumberFld.getText());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		isInserted = true;
		synchronized (this) {
			notify();
		}
		updateVisibleStatus();
	}
	
	public void ejectCard() {
		isInserted = false;
		updateVisibleStatus();
	}
	
	private void updateVisibleStatus() {
		cardNumberFld.setEnabled(!isInserted);
		cardSlot.setEnabled(!isInserted);
	}
}
