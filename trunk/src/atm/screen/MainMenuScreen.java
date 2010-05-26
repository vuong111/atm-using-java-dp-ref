package atm.screen;

public class MainMenuScreen extends ScreenType {
	
	@Override
	public int getTypeCode() {
		return ScreenType.MAIN_MENU_TYPE;
	}
	
	@Override
	protected String getImageName() {
		return "mainmenu.png";
	};
	
	@Override
	protected String getImageDescription() {
		return "Main Menu";
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