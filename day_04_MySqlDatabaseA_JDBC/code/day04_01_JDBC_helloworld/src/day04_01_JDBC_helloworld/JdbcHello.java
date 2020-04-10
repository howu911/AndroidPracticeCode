package day04_01_JDBC_helloworld;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import org.junit.Test;

import com.mysql.jdbc.Driver;

public class JdbcHello {
	
	private ResultSet resultSet;
	private Statement statement;
	private Connection connection;
	@Test
	public void jdbcHello() {
		try {
			//ע������
			DriverManager.registerDriver(new Driver());
			//����
			String url = "jdbc:mysql://localhost:3306/stu";
			String user = "root";
			String password = "123456";
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			//ִ�����
			String sql = "select * from info";
			resultSet = statement.executeQuery(sql );
			//����next�����ƶ��������һ��
			while(resultSet.next()){
				String name = resultSet.getString(2);
				System.out.println(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.releaseResource(resultSet, statement, connection);
		}
	}
	
	@Test
	public void jdbcInsert() {
		connection = JDBCUtils.getConnection();
		try {
			statement = connection.createStatement();
			String sql = "insert into info values(5,'lisi')";
			int result = statement.executeUpdate(sql);
			System.out.println("������"+result+"������");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.releaseResource(resultSet, statement, connection);
		}
	}
}
