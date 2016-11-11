package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {
	public static Connection getConnection()throws Exception{
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.71.90:1521:orcl", "huawangxin", "huawangxin");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return conn;
	}
	public static void close(Connection conn) throws Exception{
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
	}	
	
	public static void main(String[] args) throws Exception{
		System.out.println(
				getConnection());
	}
}
