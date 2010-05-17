package atm.screen;

import atm.utils.ATMUtils;

public class WithdrawScreen extends Screen {
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
	
	//no displays..
}
