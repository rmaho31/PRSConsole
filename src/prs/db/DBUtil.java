package prs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static Connection getConnection() throws SQLException{
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/prs?useSSL=false", "prs_user", "sesame");
		return connect;
	}
	
	public static void closeConnection(Connection connect) throws SQLException{
		if(connect != null) {
			connect.close();
		}
	}
}
