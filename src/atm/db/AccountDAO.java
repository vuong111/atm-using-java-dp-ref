package atm.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import atm.Account;

public class AccountDAO {
	private static Connection connection = DBConnection.getInstance();
	
	public Account find(int theAccountNumber) {
		
		String sql = "SELECT * FROM Account WHERE accountNumber = " + theAccountNumber;
		
		Account account = null;
		
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
		
		return account;
	}
	
//	public boolean login(int theAccountNumber, int thePIN) {
//		Account account = find(theAccountNumber);
//		if (account != null) {
//			return account.validatePIN(thePIN);
//		}
//		return false;
//	}
	
	public ArrayList<Account> getAccounts() {			
		String sql = "SELECT * FROM Account";
		
		ArrayList<Account> accounts = new ArrayList<Account>();
		
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while (rs.next()) {
				int accountNumber = rs.getInt("accountNumber");
				String fullName = rs.getString("fullName");
				int pin = rs.getInt("pin");
				double availableBalance = rs.getDouble("availableBalance");
				double totalBalance = rs.getDouble("totalBalance");
				accounts.add(new Account(accountNumber, fullName, pin, availableBalance, totalBalance));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}			
		
		return accounts;
	}
	
	public void updateAccount(Account theAccount) {
		String sql = "UPDATE Account SET availableBalance = " + theAccount.getAvailableBalance() +
			", totalBalance = " + theAccount.getTotalBalance() + 
			" WHERE accountNumber = " + theAccount.getAccountNumber();
		
		try {
			Statement stm = connection.createStatement();
			System.out.println(stm.executeUpdate(sql));
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public void changePIN(int theAccoutNumber, int theNewPIN) {
		String sql = "UPDATE Account SET pin = " + theNewPIN + " WHERE accountNumber = " + theAccoutNumber;
		
		try {
			Statement stm = connection.createStatement();
			System.out.println(stm.executeUpdate(sql));
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
