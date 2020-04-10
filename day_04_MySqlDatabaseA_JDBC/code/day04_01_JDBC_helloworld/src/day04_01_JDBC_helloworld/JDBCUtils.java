package day04_01_JDBC_helloworld;

import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;


public class JDBCUtils {
	
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	static {
		//º”‘ÿæ≤Ã¨≈‰÷√Œƒº˛
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("jdbc.properties"));
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			username = properties.getProperty("username");
			password  = properties.getProperty("password");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void releaseResource(ResultSet resultSet, Statement statement, Connection connection) {
		if(resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static Connection getConnection() {
		// TODO Auto-generated method stub
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, username, password);
			return connection;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		
		
	}

	
}
