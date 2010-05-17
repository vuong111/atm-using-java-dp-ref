package atm.screen;

import atm.utils.ATMUtils;

public class MainMenuScreen extends Screen {
	private static final String IMAGE_NAME = "mainmenu.png";
	private static final String IMAGE_DESCRIPTION = "Main Menu";
	
	@Override
	protected String getImageName() {
		return IMAGE_NAME;
	};
	
	@Override
	protected String getImageDescription() {
		return IMAGE_DESCRIPTION;
	};
	
	@Override
	protected void addComponents() {
		//no components..		
	}
	
	//no displays..
}