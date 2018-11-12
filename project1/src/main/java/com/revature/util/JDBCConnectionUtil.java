package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import oracle.jdbc.OracleDriver;

public class JDBCConnectionUtil {

	public static Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@revature.cgc1ojysto0h.us-east-1.rds.amazonaws.com:1521:ORCL";
		String username = "project1";
		String password = "password";
		
		OracleDriver driver = new OracleDriver();
		DriverManager.registerDriver(driver);
		
		return DriverManager.getConnection(url, username, password);
	}
}
