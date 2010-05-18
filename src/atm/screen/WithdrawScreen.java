package atm.screen;

import atm.utils.ATMUtils;

public class WithdrawScreen extends ScreenType {
	private static final String IMAGE_NAME = "withdraw.png";
	private static final String IMAGE_DESCRIPTION = "Withdraw";

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
