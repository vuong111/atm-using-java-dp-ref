package atm.screen;

import atm.utils.ATMUtils;

public class WelcomeScreen extends Screen {
	
	@Override
	protected void configBackgroundImage() {
		bgImage = ATMUtils.createImageIcon(this.getClass(), "images/welcome.png", "Transfer").getImage();		
	}
	
	@Override
	public void addComponents() {
		//no components..
	}
}
