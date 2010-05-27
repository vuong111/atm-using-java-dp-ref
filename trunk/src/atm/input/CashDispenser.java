package atm.input;

import javax.swing.JButton;

public class CashDispenser extends JButton {	
	private final static int CASH_TYPE = 50; //giả sử ATM chỉ cung cấp tờ tiền loại 50k..
	private final static int INITIAL_COUNT = 1000; //this should be loaded from database.. :)
	private int count; // số tờ tiền có trong ATM..
   
	public CashDispenser() {
		super("Khay rút tiền");
		count = INITIAL_COUNT;
	}
	
	//cập nhập số tờ tiền trong ATM sau khi rút..
	public void dispenseCash( int amount ) {
		int billsRequired = amount / CASH_TYPE; //số tờ tiền cần rút
		count -= billsRequired;
	} 

   // kiểm tra xem ATM còn đủ tiền để rút không
	public boolean isSufficientCashAvailable(int amount) {
		int billsRequired = amount / CASH_TYPE; //số tờ tiền cần rút

		if ( count >= billsRequired  )
			return true;
		else 
			return false;
	}
}
