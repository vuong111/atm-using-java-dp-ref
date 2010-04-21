package atm.gui.screen;   

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JTextField;

import atm.utils.ATMUtils;

public class LoginScreen extends JPanel {
	//private JTextField accountNumberFld = new JTextField("12345", 11);
	private JTextField pinFld = new JTextField(11);
	
	private Image bgImage;
	
	public LoginScreen() {	    
		initComponents();
	}
	
	private void initComponents() {	    
		bgImage = ATMUtils.createImageIcon(this.getClass(), "images/login.png", "Login").getImage();
		Dimension size = new Dimension(bgImage.getWidth(null), bgImage.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
		
		//pin field
		add(pinFld);
		pinFld.setBackground(Color.green);
		pinFld.setBounds(165, 165, 140, 26);
		pinFld.setForeground(Color.red);
		pinFld.setEditable(false);
	}
	
	/** display PIN code **/
	public void display(String s) {
		pinFld.setText(s);
	}
	
	/** clear display **/	
	public void clearDisplay() {
		display("");
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    g.drawImage(bgImage, 0, 0, null);
	}

}