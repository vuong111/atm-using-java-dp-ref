package atm.gui.screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JTextField;

import atm.utils.ATMUtils;

public class ChangePINScreen extends JPanel {
	private JTextField pinFld = new JTextField(11);
	private JTextField pinConfirmFld = new JTextField(11);
	
	private Image bgImage;
	
	public ChangePINScreen() {
		bgImage = ATMUtils.createImageIcon(this.getClass(), "images/changePIN.png", "Change Pin").getImage();
		Dimension size = new Dimension(bgImage.getWidth(null), bgImage.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	    
	    initComponents();
	}
	
	private void initComponents() {
		//PIN field 1
		add(pinFld);
		pinFld.setBackground(Color.green);
		pinFld.setBounds(165, 131, 140, 26);
		pinFld.setForeground(Color.red);
		//pinFld.setEditable(false);
		
		//PIN field 2
		add(pinConfirmFld);
		pinConfirmFld.setBackground(Color.green);
		pinConfirmFld.setBounds(165, 165, 140, 26);
		pinConfirmFld.setForeground(Color.red);
		//pinConfirmFld.setEditable(false);
	}
	
	public void paintComponent(Graphics g) {
	    g.drawImage(bgImage, 0, 0, null);
	}
}
