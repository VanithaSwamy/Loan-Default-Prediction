package org.standaloneApp.repository;

import java.io.*;
import java.sql.*;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class DBConfig {
	protected static Connection conn;
	protected static PreparedStatement stmt;
	protected static CallableStatement cstmt;
	protected static ResultSet rs;
	private static DBConfig db;

	private static Logger logger = Logger.getLogger(DBConfig.class);//logger object created
	static {
		PropertyConfigurator.configure(new File("").getAbsolutePath()+"\\src\\main\\resources\\application.properties");
		logger.setLevel(Level.DEBUG);
	}
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
			{
				System.out.println("Databases connected");
				logger.info("Database Connected..LogMSG");
			}
			else
			{
				System.out.println("Database not connected");
				logger.error("Database Not Connected..LogMSG");
			}
				
		} catch (Exception e) {
			System.out.println("Error is " + e.getMessage());
			logger.error("Unable to Connect the Database");
		}
	}

	public static DBConfig getInstance() {
		if (db == null) {
			db = new DBConfig();
			logger.info("DBConfig Object created successfully.....");
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
