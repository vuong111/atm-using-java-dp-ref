package atm.screen;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import atm.utils.ATMUtils;

public abstract class Screen extends JPanel {
	private String imageFolder = "images";
	private Image bgImage;

	public Screen() {
		initComponents();
	}

	private void initComponents() { //form template method..
		configBackgroundImage();
		configSize();
		addComponents();
	}
	
	protected void configBackgroundImage() {
		bgImage = ATMUtils.createImageIcon(this.getClass(), imageFolder + "/" + 
				getImageName(), getImageDescription()).getImage();
	}
	
	protected void configSize() {
		Dimension size = new Dimension(bgImage.getWidth(null), bgImage.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	}

	abstract protected void addComponents();
	abstract protected String getImageName();
	abstract protected String getImageDescription();
	
	/*
	 * displayMessage(int index, String msg) 
	 * => Replace Parameter with Explicit Methods
	 */	
	public void displayMessage1(String msg) {
		//subclasses should override this..		
	}
	
	public void displayMessage2(String msg) {
		//subclasses should override this..
	}

	public void displayMessage3(String msg) {
		//subclasses should override this..
	}
	
	public void clearDisplay() {
		//subclasses should override this..
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    g.drawImage(bgImage, 0, 0, null);
	}

}