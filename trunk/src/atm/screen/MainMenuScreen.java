package atm.screen;

public class MainMenuScreen extends ScreenType {
	private static final String IMAGE_NAME = "mainmenu.png";
	private static final String IMAGE_DESCRIPTION = "Main Menu";
	
	@Override
	public int getTypeCode() {
		return ScreenType.MAIN_MENU_TYPE;
	}
	
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
	
	@Override
	public void printMessage(String msg, int pos) {
		//print nothing..
	}
	
	@Override
	public void displayInput(String msg) {
		//display nothing..
	}
}