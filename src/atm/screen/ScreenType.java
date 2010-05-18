package atm.screen;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import atm.utils.ATMUtils;

public abstract class ScreenType extends JPanel {	
	public static final int WELCOME_TYPE = 1;
	public static final int LOGIN_TYPE = 2;
	public static final int MAIN_MENU_TYPE = 3;
	public static final int VIEW_TYPE = 4;
	public static final int WITHDRAW_TYPE = 5;
	public static final int CHANGE_PIN_TYPE = 6;
	public static final int TRANSFER1_TYPE1 = 7;
	public static final int TRANSFER2_TYPE = 8;	
	
	private String imageFolder = "images";
	private Image bgImage;	

	public ScreenType() {
		initComponents();
	}
	
	public static ScreenType newType(int type) {		
		switch (type) {
		case WELCOME_TYPE:
			return new WelcomeScreen();
		case LOGIN_TYPE:
			return new LoginScreen();
		case MAIN_MENU_TYPE:
			return new MainMenuScreen();
		case VIEW_TYPE:
			return new BalanceInquiryScreen();
		case WITHDRAW_TYPE:
			return new WithdrawScreen();
		case CHANGE_PIN_TYPE:
			return new ChangePINScreen();
		case TRANSFER1_TYPE1:
			return new TransferScreen1();
		case TRANSFER2_TYPE:
			return new TransferScreen2();
		default:
			return null;
		}
	}
	
	/* FORM TEMPLATE METHOD * 
	 */
	private void initComponents() {
		configBackgroundImage();
		configSize();
		addComponents();
	}
	
	/**
	 * Form template methods..
	 */
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
	
	/**
	 * Print & display methods
	 */
	abstract public void printMessage(String msg, int pos);
	
	abstract public void displayInput(String msg);
	
	public void clearDisplay() {
		displayInput("");
	}
	
	/** 
	 * Override paintComponent(g)
	 */
	@Override
	public void paintComponent(Graphics g) {
	    g.drawImage(bgImage, 0, 0, null);
	}

}