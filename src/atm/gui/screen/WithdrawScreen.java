package atm.gui.screen;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import atm.utils.ATMUtils;

public class WithdrawScreen extends JPanel {
	private Image bgImage;
	
	public WithdrawScreen() {
		bgImage = ATMUtils.createImageIcon(this.getClass(), "images/withdraw.png", "Withdraw").getImage();
		Dimension size = new Dimension(bgImage.getWidth(null), bgImage.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	    
		initComponents();
	}
	
	private void initComponents() {
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    g.drawImage(bgImage, 0, 0, null);
	}
}
