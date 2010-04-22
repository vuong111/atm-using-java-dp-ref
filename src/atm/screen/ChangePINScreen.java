package atm.screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import atm.utils.ATMUtils;

public class ChangePINScreen extends JPanel {
	public static final String ENTER_PIN = "Nhập mã PIN: ";
	public static final String CONFIRM_PIN = "Xác nhận mã PIN: ";
	
	private JLabel pinLbl = new JLabel(ENTER_PIN);
	private JTextField pinFld = new JTextField(11);
	
	private Image bgImage;
	
	public ChangePINScreen() {	    
	    initComponents();
	}
	
	private void initComponents() {
		bgImage = ATMUtils.createImageIcon(this.getClass(), "images/changePIN.png", "Change Pin").getImage();
		Dimension size = new Dimension(bgImage.getWidth(null), bgImage.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	    
		//PIN label
		add(pinLbl);
		pinLbl.setBackground(Color.black);
		pinLbl.setBounds(40, 165, 140, 26);
		pinLbl.setForeground(Color.red);
		
		//PIN field
		add(pinFld);
		pinFld.setBackground(Color.green);
		pinFld.setBounds(165, 165, 140, 26);
		pinFld.setForeground(Color.red);
		pinFld.setEditable(false);
	}
	
	/** display PIN code **/
	public void display(final String msg) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				pinFld.setText(msg);		
			}
		});
	}
	
	/** clear PIN display **/	
	public void clearDisplay() {
		display("");
	}
	
	/** show PIN request message **/
	public void showMessage(final String msg) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				pinLbl.setText(msg);
			}
		});		
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    g.drawImage(bgImage, 0, 0, null);
	}
}
