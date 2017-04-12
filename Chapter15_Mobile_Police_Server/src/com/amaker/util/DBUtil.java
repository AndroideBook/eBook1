package com.amaker.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * @author hz.guo
 *	資料庫工具類別
 */
public class DBUtil {
	
	/*
	 * 關閉資料庫連線
	 */
	public void closeConn(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 建立資料庫連線
	 */
	public Connection openConnection() {
		Properties prop = new Properties();
		String driver = null;
		String url = null;
		String username = null;
		String password = null;

		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream(
					"DBConfig.properties"));

			/*
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			*/
			
  			url = "jdbc:mysql://localhost:3306/test2?characterEncoding=utf-8";
  			driver = "com.mysql.jdbc.Driver";
			username = "admin";
			password = "123";
			
			Class.forName(driver);
			return DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
