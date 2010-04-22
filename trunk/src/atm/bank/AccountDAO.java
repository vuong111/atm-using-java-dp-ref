package atm.bank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AccountDAO {
	private static Connection connection = DBConnection.getInstance();
	
	public Account getAccount(int theAccountNumber) {
		
		String sql = "SELECT * FROM Account WHERE accountNumber = " + theAccountNumber;
		
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			if (rs.next()) {
				int accountNumber = rs.getInt("accountNumber");
				String fullName = rs.getString("fullName");
				int pin = rs.getInt("pin");
				double availableBalance = rs.getDouble("availableBalance");
				double totalBalance = rs.getDouble("totalBalance");
				return new Account(accountNumber, fullName, pin, availableBalance, totalBalance);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return null;
	}
	
	public void updateAccount(Account userAccount) {
		String sql = "UPDATE Account SET " +
				" availableBalance = " + userAccount.getAvailableBalance() +
				", totalBalance = " + userAccount.getTotalBalance() +
				", pin = " + userAccount.getPIN() +
				" WHERE accountNumber = " + userAccount.getAccountNumber();
		
		try {
			Statement stm = connection.createStatement();
			stm.executeUpdate(sql);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public void createAccount(Account userAccount) {
		
	}
	
	public void deleteAccount(Account userAccount) {
		
	}
}
