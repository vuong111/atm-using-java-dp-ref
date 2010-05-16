package atm.screen;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public abstract class Screen extends JPanel {
	protected Image bgImage;

	public Screen() {
		initComponents();
	}

	private void initComponents() {
		configBackgroundImage();
		configSize();
		addComponents();
	}
	
	abstract protected void configBackgroundImage();
	
	abstract protected void addComponents();
	
	protected void configSize() {
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