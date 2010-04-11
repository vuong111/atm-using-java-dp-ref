package atm.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import atm.Account;

public class AccountDAO {
	private static Connection connection = DBConnection.getInstance();
	
	public ArrayList<Account> getAccounts() {			
		ArrayList<Account> accounts = new ArrayList<Account>();
		
		String sql = "SELECT * FROM Account";
		
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while (rs.next()) {
				int accountNumber = rs.getInt("accountNumber");
				int pin = rs.getInt("pin");
				double availableBalance = rs.getDouble("availableBalance");
				double totalBalance = rs.getDouble("totalBalance");
				accounts.add(new Account(accountNumber, pin, availableBalance, totalBalance));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}			
		
		return accounts;
	}
	
	public void updateAccount(Account account) {
		String sql = "UPDATE Account SET availableBalance = " + account.getAvailableBalance() +
			", totalBalance = " + account.getTotalBalance() + 
			" WHERE accountNumber = " + account.getAccountNumber();
		
		try {
			Statement stm = connection.createStatement();
			stm.executeUpdate(sql);			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
