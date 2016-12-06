package com.jp.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestMysqlConfiguration {
	static String driver = "com.mysql.jdbc.Driver";

	static String dbName = "test";

	static String passwrod = "zhanghan";

	static String userName = "root";

	static String url = "jdbc:mysql://localhost:3306/" + dbName;

	static String sql = "select * from test";
	
	public static void makeCLUD(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;

		try {
			// 加载MySql的驱动类
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(url, userName,passwrod);
		
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("id : " + rs.getInt(1) + " name : "+ rs.getString(2) + " password : " + rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		makeCLUD();
	}
}
