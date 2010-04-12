package atm.gui.input;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import atm.gui.observer.Observable;
import atm.gui.observer.Observer;

public class Keypad implements ActionListener, Observable {
	
	public static final String ENTER = "Enter";
	public static final String CANCEL = "Cancel";
	public static final String CLEAR = "Clear";
	public static final String LEFT_KEY1 ="LEFT_KEY1";
	public static final String LEFT_KEY2 ="LEFT_KEY2";
	public static final String LEFT_KEY3 ="LEFT_KEY3";
	public static final String LEFT_KEY4 ="LEFT_KEY4";
	public static final String RIGHT_KEY1 ="RIGHT_KEY1";
	public static final String RIGHT_KEY2 ="RIGHT_KEY2";
	public static final String RIGHT_KEY3 ="RIGHT_KEY3";
	public static final String RIGHT_KEY4 ="RIGHT_KEY4";
	
	private JPanel leftKeypad;
	private JPanel rightKeypad;
	private JPanel numberKeypad;
	private JPanel operationKeypad;
	
	private ArrayList<Observer> observerList = new ArrayList<Observer>(); 
	private String keyPressed = "";
	
	public Keypad() {
		initLeftKeypad();
		initRightKeypad();
		initNumberKeypad();
		initOperationKeypad();
	}
	
	public JPanel getLeftKeypad() {
		return leftKeypad;
	}
	
	public JPanel getRightKeypad() {
		return rightKeypad;
	}
	
	public JPanel getNumberKeypad() {
		return numberKeypad;
	}
	
	public JPanel getOperationKeypad() {
		return operationKeypad;
	}
	
	private void initLeftKeypad() {
		//LEFT_KEY1->4
	    leftKeypad = new JPanel();
	    leftKeypad.setLayout(new BoxLayout(leftKeypad, BoxLayout.Y_AXIS));
	    leftKeypad.add(Box.createGlue());
	    leftKeypad.add(Box.createGlue());
	    leftKeypad.add(Box.createGlue());
	    for (int i = 1; i <= 4; i++) {
	    	leftKeypad.add(Box.createGlue());
	    	JButton btn = new JButton("      ");
	    	btn.setActionCommand("LEFT_KEY" + i);
	    	btn.addActionListener(this);
	    	leftKeypad.add(btn);	    	
	    }
	    leftKeypad.add(Box.createGlue());
	    leftKeypad.setBackground(Color.red);
	    leftKeypad.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
	}
	
	private void initRightKeypad() {
		//RIGHT_KEY1->4
		rightKeypad = new JPanel();
		rightKeypad.setLayout(new BoxLayout(rightKeypad, BoxLayout.Y_AXIS));
		rightKeypad.add(Box.createGlue());
		rightKeypad.add(Box.createGlue());
		rightKeypad.add(Box.createGlue());
	    for (int i = 1; i <= 4; i++) {
	    	rightKeypad.add(Box.createGlue());
	    	JButton btn = new JButton("      ");
	    	btn.setActionCommand("RIGHT_KEY" + i);
	    	btn.addActionListener(this);
	    	rightKeypad.add(btn);	    	
	    }
	    rightKeypad.add(Box.createGlue());
	    rightKeypad.setBackground(Color.red);
	    rightKeypad.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
	}
	
	private void initNumberKeypad() {
		numberKeypad = new JPanel(new GridLayout(0, 3));
		//key 1->9
	    for (int i = 1; i <= 9; i++) {
	    	JButton keyBtn = new JButton(i + "");	    	
	    	keyBtn.addActionListener(this);
	    	numberKeypad.add(keyBtn);
	    }
	    //null key
	    numberKeypad.add(new JButton(""));
	    
	    //key 0
	    JButton keyBtn = new JButton("0");
	    keyBtn.addActionListener(this);
	    numberKeypad.add(keyBtn);
	    
	    //null key
	    numberKeypad.add(new JButton(""));
	}
	
	private void initOperationKeypad() {
		operationKeypad = new JPanel(new GridLayout(0, 1));
		
		//cancel key
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(this);
		operationKeypad.add(cancelBtn);
		
		//clear key
		JButton clearBtn = new JButton("Clear");
		clearBtn.addActionListener(this);
		operationKeypad.add(clearBtn);
		
		//button key
		JButton enterBtn = new JButton("Enter");
		enterBtn.addActionListener(this);
		operationKeypad.add(enterBtn);
		
		//null key
		operationKeypad.add(new JButton(""));
	}
	
	public String getKeyPressed() {
		return keyPressed;
	}
	
	/**
	 * implement ActionListener's method
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		keyPressed = e.getActionCommand();
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
