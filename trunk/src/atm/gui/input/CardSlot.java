package atm.gui.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import atm.gui.observer.Observable;
import atm.gui.observer.Observer;

public class CardSlot extends JButton implements ActionListener, Observable {
	/**
	 * observers list
	 */
	private ArrayList<Observer> observerList = new ArrayList<Observer>();
	
	private boolean isInserted = false;
	
	public CardSlot() {
		super("Card Slot");
		addActionListener(this);
	}
	
	public boolean isInserted() {
		return isInserted;
	}
	
	/**
	 * implement ActionListener's method
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		isInserted = true;
		notifyObservers();
	}
	
	/**
	 * implement Observable's methods
	 */
	@Override
	public void addObserver(Observer o) {
		observerList.add(o);
		
	}
	
	@Override
	public void deleteObserver(Observer o) {
		observerList.remove(o);
		
	}
	
	@Override
	public void deleteObservers() {
		observerList = new ArrayList<Observer>();
		
	}
	
	@Override
	public void notifyObservers() {
		for (Observer o : observerList) {
			o.update(this);
		}
	}
}
