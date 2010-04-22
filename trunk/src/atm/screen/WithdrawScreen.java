package atm.screen;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import atm.utils.ATMUtils;

public class WithdrawScreen extends JPanel {
	private Image bgImage;
	
	public WithdrawScreen() {    
		initComponents();
	}
	
	private void initComponents() {
		bgImage = ATMUtils.createImageIcon(this.getClass(), "images/withdraw.png", "Withdraw").getImage();
		Dimension size = new Dimension(bgImage.getWidth(null), bgImage.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    g.drawImage(bgImage, 0, 0, null);
	}
}
