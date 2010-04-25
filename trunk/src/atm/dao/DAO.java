package atm.dao;

import java.sql.Connection;

public abstract class DAO<T> {
	protected Connection connection = null;
	
	public DAO(Connection conn) {
		connection = conn;
	}
	
	public abstract boolean insert(T obj);
	
	public abstract boolean delete(T obj);
	
	public abstract boolean update(T obj);
	
	public abstract T find(double id);
}
