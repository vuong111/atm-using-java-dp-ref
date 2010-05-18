package atm.screen;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Screen extends JPanel {
	
	/* REPLACE TYPE CODE WITH STRATEGY/STATE
	 * REPLACE CONDITIONAL WITH POLYMORPHISM
	 */
	
	/** screenType - current screen **/
	private ScreenType screenType;
	
	/** constructor **/
	public Screen() {
		initComponents();
		
		setScreenType(ScreenType.WELCOME_TYPE); //+ để lưu giữ size của screen ban đầu..
	}
	
	private void initComponents() {
		setBackground(Color.GREEN);
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));		
	}
	
	private void showScreen() {
		removeAll();
		add(screenType);		
		validate();	//repaint();
	}
	
	/** set screen type **/
	public void setScreenType(int type) {
		screenType = ScreenType.newType(type);
		showScreen();
	}
	
	public ScreenType getScreenType() {
		return screenType;
	}	
	
	/** print & display methods.. **/
	public void printMessage(String msg, int posIndex) {
		getScreenType().printMessage(msg, posIndex);
	}
	
	public void displayInput(String msg) {
		getScreenType().displayInput(msg);
	}
	
	public void clearDisplay() {
		getScreenType().clearDisplay();
	}
}