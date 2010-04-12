package atm.gui.screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JTextField;

import atm.utils.ATMUtils;

public class TransferScreen extends JPanel {
	private JTextField accountNumberFld = new JTextField(11);	
	private Image bgImage;
	
	public TransferScreen() {
		bgImage = ATMUtils.createImageIcon(this.getClass(), "images/transfer.png", "Transfer").getImage();
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
		add(accountNumberFld);
		accountNumberFld.setBackground(Color.green);
		accountNumberFld.setBounds(165, 131, 140, 26);
		accountNumberFld.setForeground(Color.red);
		//pinFld.setEditable(false);
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    g.drawImage(bgImage, 0, 0, null);
	}
}
