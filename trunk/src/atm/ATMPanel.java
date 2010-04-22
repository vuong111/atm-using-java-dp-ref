package atm;
//package atm.gui;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.GridLayout;
//import javax.swing.BorderFactory;
//import javax.swing.JPanel;
//
//public class ATMPanel extends JPanel {
//	
//	public ATMPanel(ATMWindow atm) {
//		JPanel vPanel = new JPanel(new GridLayout(0, 1));		
//		vPanel.add(atm.getCardSlot());			//cardSlot
//		
//		vPanel.add(atm.getCashDispenser());		//cashDispenser
//		vPanel.add(atm.getDepositSlot());		//depositSlot
//		vPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
//		
//		JPanel ioPanel = new JPanel();		
//		ioPanel.add(atm.getKeypad().getNumberKeypad());				//keypad - number keypad		
//		ioPanel.add(atm.getKeypad().getOperationKeypad()); 			//keypad - operation keypad		
//		ioPanel.add(vPanel);
//		ioPanel.setBackground(new Color(51, 153, 204));
//		
//		add(atm.getWelcomePanel(), BorderLayout.NORTH);				//welcome
//		add(atm.getKeypad().getLeftKeypad(), BorderLayout.WEST);		//keypad - left side		
//		add(atm.getKeypad().getRightKeypad(), BorderLayout.EAST); 	//keypad - right side		
//		add(atm.getScreen(), BorderLayout.CENTER); 				  	//screen		
//		add(ioPanel, BorderLayout.SOUTH);
//	}
//}
