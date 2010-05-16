package atm.screen;

import atm.utils.ATMUtils;

public class WithdrawScreen extends Screen {

	@Override
	protected void configBackgroundImage() {
		bgImage = ATMUtils.createImageIcon(this.getClass(), "images/withdraw.png", "Withdraw").getImage();		
	}
	
	@Override
	public void addComponents() {
		//no components..
	}
}
