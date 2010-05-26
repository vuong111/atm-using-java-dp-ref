package atm.screen;

public class WelcomeScreen extends ScreenType {
	
	@Override
	public int getTypeCode() {
		return ScreenType.WELCOME_TYPE;
	}
	
	@Override
	protected String getImageName() {
		return "welcome.png";
	};
	
	@Override
	protected String getImageDescription() {
		return "Welcome";
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
