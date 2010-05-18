package atm.dao;

import java.sql.Connection;

import atm.bean.Account;
import atm.bean.TransactionVO;

public class DAOFactory {
	public static final Connection connection = DBConnection.getInstance();
	
	public static DAO<Account> getAccountDAO() {
		return new AccountDAO(connection);
	}
	
	public static DAO<TransactionVO> getTransactionDAO() {
		return new TransactionDAO(connection);
	}
}
