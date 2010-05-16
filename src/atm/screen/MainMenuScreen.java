package atm.screen;

import atm.utils.ATMUtils;

public class MainMenuScreen extends Screen {

	@Override
	protected void configBackgroundImage() {
		bgImage = ATMUtils.createImageIcon(this.getClass(), "images/mainmenu.png", "Main Menu").getImage();
	}
	
	@Override
	protected void addComponents() {
		//no components..		
	}
}