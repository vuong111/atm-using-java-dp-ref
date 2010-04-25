package atm.dao;

import java.sql.Connection;

import atm.bean.Account;
import atm.bean.Transaction;

public class DAOFactory {
	public static final Connection connection = DBConnection.getInstance();
	
	public static DAO<Account> getAccountDAO() {
		return new AccountDAO(connection);
	}
	
	public static DAO<Transaction> getTransactionDAO() {
		return new TransactionDAO(connection);
	}
}
