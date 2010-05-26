package atm.screen;

public class ChangePINScreen2 extends ChangePINScreen1 {

	@Override
	public int getTypeCode() {
		return ScreenType.CHANGE_PIN2_TYPE;
	}
	
	@Override
	public String getImageName() {
		return "changePIN2.png";
	};
	
	@Override
	public String getImageDescription() {
		return "Change PIN 2";
	};
}
