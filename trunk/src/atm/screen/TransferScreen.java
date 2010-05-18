//package atm.screen;
//
//import java.awt.CardLayout;
//import java.awt.Color;
//
//import javax.swing.JLabel;
//import javax.swing.JTextField;
//import javax.swing.SwingUtilities;
//
//public class TransferScreen extends Screen {	
//	public static final String ACCOUNT_PANEL = "Transfer Amount Panel";
//	public static final String MONEY_PANEL = "Transfer Money Panel";
//	
//	private String currentPanelName = ACCOUNT_PANEL;
//	
//	private AccountPanel accountPanel;
//	private MoneyPanel moneyPanel;
//	
//	private CardLayout mainLayout;
//	
//	@Override
//	protected String getImageName() {
//		return "";
//	};
//	
//	@Override
//	protected String getImageDescription() {
//		return "";
//	};
//	
//	public void addComponents() {
//		mainLayout = new CardLayout();
//		setLayout(mainLayout);
//		
//		accountPanel = new AccountPanel();
//		add(accountPanel, ACCOUNT_PANEL);
//		
//		moneyPanel = new MoneyPanel();
//		add(moneyPanel, MONEY_PANEL);
//	}
//	
//	@Override
//	protected void configSize() {
//		//no config..
//	}
//	
//	public void showPanel(String panelName) {
//		currentPanelName = panelName;
//		mainLayout.show(this, panelName);
//	}
//	
//	/** show transfer account id - on Money panel**/
//	@Override
//	public void displayMessage1(String msg) {
//		moneyPanel.displayMessage1(msg);
//	}
//	
//	/** show transfer account name - on Money panel**/
//	@Override
//	public void displayMessage2(String msg) {
//		moneyPanel.displayMessage2(msg);
//	}
//	
//	/** display accountID / money **/
//	@Override
//	public void displayMessage3(final String msg) {
//		if (currentPanelName.equals(ACCOUNT_PANEL))
//			accountPanel.displayMessage1(msg);
//		else
//			moneyPanel.displayMessage3(msg);
//	}
//	
//	/** clear display **/
//	@Override
//	public void clearDisplay() {
//		displayMessage3("");
//	}
//}
//
///**
// * Account panel
// *
// */
//class AccountPanel extends Screen {
//	private static final String IMAGE_NAME = "transfer1.png";
//	private static final String IMAGE_DESCRIPTION = "Transfer1";
//	
//	private JTextField accountFld;
//
//	@Override
//	protected String getImageName() {
//		return IMAGE_NAME;
//	};
//	
//	@Override
//	protected String getImageDescription() {
//		return IMAGE_DESCRIPTION;
//	};
//	
//	@Override
//	public void addComponents() {	
//		accountFld = new JTextField(11);	
//		
//		add(accountFld);
//		accountFld.setBackground(Color.green);
//		accountFld.setBounds(165, 131, 140, 26);
//		accountFld.setForeground(Color.red);
//		accountFld.setEditable(false);
//	}
//	
//	/** display transfer account **/
//	@Override
//	public void displayMessage1(final String msg) {
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				accountFld.setText(msg);		
//			}
//		});
//	}
//	
//	@Override
//	public void displayMessage2(String msg) {
//		//display nothing..
//	}
//	
//	@Override
//	public void displayMessage3(String msg) {
//		//display nothing..
//	}
//	
//	/** clear display **/	
//	public void clearDisplay() {
//		displayMessage1("");
//	}
//}
//
///**
// * Money panel
// *
// */
//class MoneyPanel extends Screen {
//	private static final String IMAGE_NAME = "transfer2.png";
//	private static final String IMAGE_DESCRIPTION = "Transfer2";
//	
//	private JLabel accountNumberLbl;
//	private JLabel accountNameLbl;
//	private JTextField moneyFld;	
//
//	@Override
//	protected String getImageName() {
//		return IMAGE_NAME;
//	};
//	
//	@Override
//	protected String getImageDescription() {
//		return IMAGE_DESCRIPTION;
//	};
//	
//	@Override
//	public void addComponents() {
//		accountNumberLbl = new JLabel();
//		accountNameLbl = new JLabel();
//		moneyFld = new JTextField(11);
//		
//		//transfer account number
//	    add(accountNumberLbl);
//	    accountNumberLbl.setBackground(Color.green);
//	    accountNumberLbl.setBounds(165, 105, 140, 26);
//	    accountNumberLbl.setForeground(Color.red);
//		
//		//transfer account full name
//		add(accountNameLbl);
//		accountNameLbl.setBackground(Color.green);
//		accountNameLbl.setBounds(165, 135, 140, 26);
//		accountNameLbl.setForeground(Color.red);
//		
//		//transfer account money
//		add(moneyFld);
//		moneyFld.setBackground(Color.green);
//		moneyFld.setBounds(165, 175, 140, 26);
//		moneyFld.setForeground(Color.red);
//		moneyFld.setEditable(false);		
//	}
//	
//	/** display account id **/
//	@Override
//	public void displayMessage1(final String msg) {
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				accountNumberLbl.setText(msg);	
//			}
//		});
//	}
//	
//	/** display account name **/
//	@Override
//	public void displayMessage2(final String msg) {
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				accountNameLbl.setText(msg);	
//			}
//		});
//	}
//	
//	/** display transfer money **/
//	@Override
//	public void displayMessage3(final String msg) {
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				moneyFld.setText(msg);		
//			}
//		});
//	}
//	
//	/** clear display **/	
//	public void clearDisplay() {
//		displayMessage3("");
//	}
//}
