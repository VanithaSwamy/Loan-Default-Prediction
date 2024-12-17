package org.standaloneApp.repository;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class DBConfig {
	protected static Connection conn;
	protected static PreparedStatement stmt;
	protected static CallableStatement cstmt;
	protected static ResultSet rs;
	private static DBConfig db;

	private DBConfig() {
		try {
			String path = new File("").getAbsolutePath();
			FileInputStream inputstream = new FileInputStream(path + "\\src\\main\\resources\\dbconfig.properties");
			Properties p = new Properties();
			p.load(inputstream);

			String driverClassName = p.getProperty("driver");
			String username = p.getProperty("username");
			String password = p.getProperty("password");
			String url = p.getProperty("url");

			Class.forName(driverClassName);
			conn = DriverManager.getConnection(url, username, password);

			if (conn != null)
				System.out.println("Databases connected");
			else
				System.out.println("Database not connected");

		} catch (Exception e) {
			System.out.println("Error is " + e.getMessage());
		}
	}

	public static DBConfig getInstance() {
		if (db == null) {
			db = new DBConfig();
		}
		return db;
	}

	public static Connection getConn() {
		return conn;
	}

	public static PreparedStatement getStmt() {
		return stmt;
	}

	public static CallableStatement getCallable() {
		return cstmt;
	}

	public static ResultSet getResultSet() {
		return rs;
	}
}
