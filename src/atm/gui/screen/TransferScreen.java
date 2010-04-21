package atm.gui.screen;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import atm.utils.ATMUtils;

public class TransferScreen extends JPanel {
	
	public static final String ACCOUNT_PANEL = "Transfer Amount Panel";
	public static final String MONEY_PANEL = "Transfer Money Panel";
	
	private String currentPanelName = ACCOUNT_PANEL;
	
	private AccountPanel accountPanel;
	private MoneyPanel moneyPanel;
	
	private CardLayout mainLayout;
	
	/** constructor **/
	public TransferScreen() {		
		initComponents();
	}
	
	private void initComponents() {
		mainLayout = new CardLayout();
		setLayout(mainLayout);
		
		accountPanel = new AccountPanel();
		add(accountPanel, ACCOUNT_PANEL);
		
		moneyPanel = new MoneyPanel();
		add(moneyPanel, MONEY_PANEL);
	}
	
	public void showPanel(String panelName) {
		currentPanelName = panelName;
		mainLayout.show(this, panelName);
	}
	
	public void display(final String msg) {
		if (currentPanelName.equals(ACCOUNT_PANEL))
			accountPanel.display(msg);
		else
			moneyPanel.display(msg);
	}
	
	public void clearDisplay() {
		display("");
	}
}

class AccountPanel extends JPanel {
	private JTextField accountFld = new JTextField(11);	
	private Image bgImage;
	
	public AccountPanel() {
	    initComponents();
	}
	
	private void initComponents() {
		bgImage = ATMUtils.createImageIcon(this.getClass(), "images/transfer1.png", "Transfer1").getImage();
		Dimension size = new Dimension(bgImage.getWidth(null), bgImage.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	    
		add(accountFld);
		accountFld.setBackground(Color.green);
		accountFld.setBounds(165, 131, 140, 26);
		accountFld.setForeground(Color.red);
		accountFld.setEditable(false);
	}
	
	/** display transfer account **/
	public void display(final String msg) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				accountFld.setText(msg);		
			}
		});
	}
	
	/** clear display **/	
	public void clearDisplay() {
		display("");
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    g.drawImage(bgImage, 0, 0, null);
	}
}

class MoneyPanel extends JPanel {
	private JTextField moneyFld = new JTextField(11);	
	private Image bgImage;
	
	public MoneyPanel() {	    
	    initComponents();
	}
	
	private void initComponents() {
		bgImage = ATMUtils.createImageIcon(this.getClass(), "images/transfer2.png", "Transfer2").getImage();
		Dimension size = new Dimension(bgImage.getWidth(null), bgImage.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	    
		add(moneyFld);
		moneyFld.setBackground(Color.green);
		moneyFld.setBounds(165, 170, 140, 26);
		moneyFld.setForeground(Color.red);
		moneyFld.setEditable(false);
	}
	
	/** display transfer money **/
	public void display(final String msg) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				moneyFld.setText(msg);		
			}
		});
	}
	
	/** clear display **/	
	public void clearDisplay() {
		display("");
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    g.drawImage(bgImage, 0, 0, null);
	}
}
