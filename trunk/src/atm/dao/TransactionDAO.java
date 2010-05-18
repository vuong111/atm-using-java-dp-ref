package atm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import atm.bean.TransactionVO;

public class TransactionDAO extends DAO<TransactionVO>{
	
	public TransactionDAO(Connection conn) {
		super(conn);
	}
	
	@Override
	public boolean insert(TransactionVO obj) {
		String sql = "INSERT INTO Transaction (accountNumber, referenceNumber, type, amount, balance, date_time) " +
					"VALUES (" + obj.getAccountNumber() + ", " +
								obj.getReferenceNumber() + ", '" +
								obj.getType() + "', " +
								obj.getAmount() + ", " +
								obj.getBalance() + ", '" +
								obj.getDateTime() + "'" +
							")";
		try {
			Statement stm = connection.createStatement();
			stm.executeUpdate(sql);
		}
		catch (SQLException sqle) {
			System.out.println(sql);
			sqle.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean delete(TransactionVO obj) {
		return false;
	}
	
	@Override
	public boolean update(TransactionVO obj) {
		return false;
	}
	
	@Override
	public TransactionVO find(double id) {
		String sql = "SELECT * FROM Transaction " +
					 "WHERE transaction_id = " + id;
		
		TransactionVO transaction = TransactionVO.newNull();
		
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			if (rs.next()) {
				int accountNumber = rs.getInt("accountNumber");
				int accountReference = rs.getInt("referenceNumber");
				String type = rs.getString("type");
				double amount = rs.getDouble("amount");
				double balance = rs.getDouble("balance");
				String dateTime = rs.getString("date_time");				
				
				transaction = new TransactionVO(accountNumber, accountReference, type, amount, 
										balance, dateTime);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return transaction;
	}
}
