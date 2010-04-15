package atm.gui.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {        
        private static final String  driver = "sun.jdbc.odbc.JdbcOdbcDriver";
        //private static final String path_to_db = "database/atm.mdb";
        //private static final String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ= "+ path_to_db;
        private static final String  url = "jdbc:odbc:ATM_";
        private static final String username = "";
        private static final String password = "";
        
        private static Connection connection;        
        
        private DBConnection() {
        	connection = newConnection();
        }
        
        public static synchronized Connection getInstance() {
        	if (connection == null) {
        		new DBConnection();
        	}
            return connection;
        }
        
        private Connection newConnection() {
        	Connection con = null;
        	loadDriver();
        	
            try {
            	con = DriverManager.getConnection(url, username, password);
            }
            catch (SQLException e) {                
                e.printStackTrace();
            }
            return con;
        }
        
        private void loadDriver()
        {
        	try {
                Class.forName(driver);                
            } catch(ClassNotFoundException cnfe) {
            	cnfe.printStackTrace();
            }
        }
}
