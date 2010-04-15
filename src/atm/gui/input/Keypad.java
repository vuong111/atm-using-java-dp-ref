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
import atm.utils.ATMUtils;

public class Keypad implements ActionListener, Observable {
	
	public static final int LEFT_KEY1 = 10;
	public static final int LEFT_KEY2 = 11;
	public static final int LEFT_KEY3 = 12;
	public static final int LEFT_KEY4 = 13;
	public static final int RIGHT_KEY1 = 14;
	public static final int RIGHT_KEY2 = 15;
	public static final int RIGHT_KEY3 = 16;
	public static final int RIGHT_KEY4 = 17;
	public static final int ENTER = 18;
	public static final int CANCEL = 19;
	public static final int CLEAR = 20;
	
	private JPanel leftKeypad;
	private JPanel rightKeypad;
	private JPanel numberKeypad;
	private JPanel operationKeypad;
	
	private ArrayList<Observer> observerList = new ArrayList<Observer>(); 
	
	private int pressedKeyCode;
	
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
	    for (int i = LEFT_KEY1; i <= LEFT_KEY4; i++) {
	    	leftKeypad.add(Box.createGlue());
	    	JButton btn = new JButton("      ");
	    	btn.setActionCommand(String.valueOf(i));
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
	    for (int i = RIGHT_KEY1; i <= RIGHT_KEY4; i++) {
	    	rightKeypad.add(Box.createGlue());
	    	JButton btn = new JButton("      ");
	    	btn.setActionCommand(String.valueOf(i));
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
	    	JButton keyBtn = new JButton(String.valueOf(i));    	
	    	keyBtn.addActionListener(this);
	    	numberKeypad.add(keyBtn);
	    }
	    //null key
	    numberKeypad.add(new JButton(""));
	    
	    //key 0
	    JButton keyBtn = new JButton(String.valueOf(0));
	    keyBtn.addActionListener(this);
	    numberKeypad.add(keyBtn);
	    
	    //null key
	    numberKeypad.add(new JButton(""));
	}
	
	private void initOperationKeypad() {
		operationKeypad = new JPanel(new GridLayout(0, 1));
		
		//cancel key
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setActionCommand(String.valueOf(CANCEL));
		cancelBtn.addActionListener(this);
		operationKeypad.add(cancelBtn);
		
		//clear key
		JButton clearBtn = new JButton("Clear");
		clearBtn.setActionCommand(String.valueOf(CLEAR));
		clearBtn.addActionListener(this);
		operationKeypad.add(clearBtn);
		
		//button key
		JButton enterBtn = new JButton("Enter");
		enterBtn.setActionCommand(String.valueOf(ENTER));
		enterBtn.addActionListener(this);
		operationKeypad.add(enterBtn);
		
		//null key
		operationKeypad.add(new JButton(""));
	}
	
	public int getPressedKeyCode() {
		return pressedKeyCode;
	}
	
	/**
	 * implement ActionListener's method
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		pressedKeyCode = ATMUtils.parseInt(e.getActionCommand());
		notifyObservers();
	}
	
	/**
	 * implement Observable's methods
	 */
	@Override
	public void addObserver(Observer o) {
//		deleteObservers();
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
//		for (Observer o : observerList) {
//			o.update(this);
//		}
		observerList.get(observerList.size() - 1).update(this);		
	}
}
