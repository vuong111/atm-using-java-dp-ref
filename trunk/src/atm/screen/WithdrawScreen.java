package atm.screen;

import atm.utils.ATMUtils;

public class WithdrawScreen extends ScreenType {
	
	@Override
	public int getTypeCode() {
		return ScreenType.WITHDRAW_TYPE;
	}
	
	@Override
	protected String getImageName() {
		return "withdraw.png";
	};
	
	@Override
	protected String getImageDescription() {
		return "Withdraw";
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
