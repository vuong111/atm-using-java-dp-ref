package atm.screen;

public class WelcomeScreen extends ScreenType {
	private static final String IMAGE_NAME = "welcome.png";
	private static final String IMAGE_DESCRIPTION = "Welcome";
	
	@Override
	public int getTypeCode() {
		return ScreenType.WELCOME_TYPE;
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
	public void addComponents() {
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
