package atm.input;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import atm.screen.Screen;
import atm.utils.ATMUtils;

public class Keypad {
	
	public static final int CANCELLED = -1;
	
	/** right/left keys code */
	public static final int LEFT_KEY1 = 1;
	public static final int LEFT_KEY2 = 2;
	public static final int LEFT_KEY3 = 3;
	public static final int LEFT_KEY4 = 4;
	public static final int RIGHT_KEY1 = 5;
	public static final int RIGHT_KEY2 = 6;
	public static final int RIGHT_KEY3 = 7;
	public static final int RIGHT_KEY4 = 8; //-1	
	
	/** Possible values for mode parameter to readInput() */
    public static final int LOGIN_MODE = 1;    
    public static final int MENU_MODE = 2;    
    public static final int WITHDRAW_MODE = 3;    
    public static final int BALANCE_INQUIRY_MODE = 4;    
    public static final int CHANGE_PIN_MODE = 5;    
    public static final int TRANSFER_MODE = 6; 
   
    /** readInput mode */
	private int mode;      
    
    /** Current partial line of input */
    private StringBuffer currentInput;
    
    /** Cancellation flag - set to true if user cancels */
    private boolean cancelled;
	
	/** Keypad types */
	private JPanel leftKeypad;
	private JPanel rightKeypad;
	private JPanel numberKeypad;
	private JPanel operationKeypad;
	
	/** screen */
	private Screen screen;
	
	public Keypad(Screen scr) {
		screen = scr;
		currentInput = new StringBuffer();
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
	    	leftKeypad.add(btn);

	    	// add action listener to key
	    	btn.addActionListener(new ActionListener() {
	    		@Override
	    		public void actionPerformed(ActionEvent e) {
	    			rightleftKeyPressed(ATMUtils.parseInt(e.getActionCommand()));
	    		}
	    	});
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
	    	rightKeypad.add(btn);
	    	
	    	// add action listener to key
	    	btn.addActionListener(new ActionListener() {
	    		@Override
	    		public void actionPerformed(ActionEvent e) {
	    			rightleftKeyPressed(ATMUtils.parseInt(e.getActionCommand()));
	    		}
	    	});
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
	    	numberKeypad.add(keyBtn);
	    	
	    	// add action listener to key
	    	keyBtn.addActionListener(new ActionListener() {
	    		@Override
	    		public void actionPerformed(ActionEvent e) {
	    			digitKeyPressed(ATMUtils.parseInt(e.getActionCommand()));	    			
	    		}
	    	});
	    }
	    //null key
	    numberKeypad.add(new JButton(""));
	    
	    //key 0
	    JButton keyBtn = new JButton(String.valueOf(0));
	    numberKeypad.add(keyBtn);
	    keyBtn.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			digitKeyPressed(ATMUtils.parseInt(e.getActionCommand()));
    		}
    	});
	    
	    //null key
	    numberKeypad.add(new JButton(""));
	}
	
	private void initOperationKeypad() {
		operationKeypad = new JPanel(new GridLayout(0, 1));
		
		//cancel key
		JButton cancelBtn = new JButton("Cancel");
		operationKeypad.add(cancelBtn);
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelKeyPressed();				
			}
		});
		
		//clear key
		JButton clearBtn = new JButton("Clear");
		operationKeypad.add(clearBtn);
		clearBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearKeyPressed();
			}
		});
		
		//enter key
		JButton enterBtn = new JButton("Enter");
		operationKeypad.add(enterBtn);
		enterBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enterKeyPressed();				
			}
		});
		
		//null key
		operationKeypad.add(new JButton(""));
	}
	
    public synchronized int readInput(int mode) {
    	this.mode = mode;
    	currentInput.setLength(0);
    	cancelled = false;
    	
    	try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	if (cancelled) {
    		return CANCELLED;
    	} 		
    	
    	return ATMUtils.parseInt(currentInput.toString());
    }
    
	/** Handle a digit key
    *
    *  @param digit the value on the key
    */
   private synchronized void digitKeyPressed(int digit) {
       switch (mode) {

       case LOGIN_MODE:
       case CHANGE_PIN_MODE:
    	   if (currentInput.length() == 6)
    		   break;
    	   
    	   currentInput.append(digit);
    	   
           StringBuffer echoString = new StringBuffer();
           for (int i = 0; i < currentInput.length(); i++)
               echoString.append('*');
           
           screen.displayInput(echoString.toString());
           break;
           
       case MENU_MODE:
       case WITHDRAW_MODE:
       case BALANCE_INQUIRY_MODE:
    	   break; 
       
       case TRANSFER_MODE:
    	   currentInput.append(digit);
    	   screen.displayInput(currentInput.toString());
    	   break;
    	   
       }
   }
   
   /** Handle the ENTER key
    */
   private synchronized void enterKeyPressed() {
	   switch(mode) {

       case LOGIN_MODE:
       case CHANGE_PIN_MODE:
       case TRANSFER_MODE:
    	   if (currentInput.length() > 0)
               notify();
    	   break;
    	   
       case MENU_MODE:
       case WITHDRAW_MODE:
    	   break;
    	   
       case BALANCE_INQUIRY_MODE:
    	   //...
    	   notify();
    	   break;
    	   
       }
   }
               
   /** Handle the CLEAR key
    */
   private synchronized void clearKeyPressed() {
       switch(mode) {

       case LOGIN_MODE:       
       case CHANGE_PIN_MODE:
       case TRANSFER_MODE:
    	   currentInput.setLength(0);
           screen.clearDisplay();
    	   break;
           
       case MENU_MODE:    	   
       case WITHDRAW_MODE:
       case BALANCE_INQUIRY_MODE:
    	   break;    	   
       
       }
   }           
               
   /** Handle the CANCEL KEY
    */
   private synchronized void cancelKeyPressed() {
       switch(mode) {

       case LOGIN_MODE:
       case MENU_MODE:
       case WITHDRAW_MODE:
       case BALANCE_INQUIRY_MODE:
       case CHANGE_PIN_MODE:
       case TRANSFER_MODE:
    	   cancelled = true;
           notify();
           break;
           
       }
   }
   
   /** Handle the RIGHT & LEFT keys
    */
   private synchronized void rightleftKeyPressed(int keyCode) {
	   switch (mode) {

	   case LOGIN_MODE:
	   case CHANGE_PIN_MODE:
	   case TRANSFER_MODE:
		   if ((keyCode == RIGHT_KEY3) && (currentInput.length() > 0)) { //enter
			   notify();
		   }
		   else if (keyCode == RIGHT_KEY4) { //cancel
			   cancelled = true; //-> readInput() return -1; !!!
			   notify();
		   }
		   break;
		   
	   case MENU_MODE:
		   if (keyCode == RIGHT_KEY4)
			   cancelled = true; //-> readInput() return -1; !!!
		   else
			   currentInput.append(keyCode);
		   
		   notify();
		   break;
	   
	   case WITHDRAW_MODE:
     	   if (keyCode == LEFT_KEY4)
    		   break;
     	   
    	   if (keyCode == RIGHT_KEY4)
    		   cancelled = true; //-> readInput() return -1; !!!
    	   else                   
    		   currentInput.append(keyCode);
    		   
    	   notify();
    	   break;

	   case BALANCE_INQUIRY_MODE:
		   if (keyCode == RIGHT_KEY3) {
			   //...
			   notify();
		   }
		   else if (keyCode == RIGHT_KEY4) {
			   cancelled = true;
			   notify();
		   }
		   break;
		   
	   }
   }
}
