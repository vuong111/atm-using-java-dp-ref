package atm.gui.input;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import atm.gui.observer.Observable;
import atm.gui.observer.Observer;
import atm.utils.ATMUtils;

public class CardSlot extends JPanel implements ActionListener {
	/**
	 * observers list
	 */
	private JTextField cardNumberFld;
	private JButton cardSlot;
	
	private boolean isInserted = false;
	
	public CardSlot() {
		setLayout(new GridLayout(0, 1));
		
		cardNumberFld = new JTextField("12345");
		cardSlot = new JButton("Insert your card");
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
	
	/**
	 * implement ActionListener's method
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		isInserted = true;
		synchronized (this) {
			notify();
		}
		updateVisibleStatus();
		//notifyObservers();
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
