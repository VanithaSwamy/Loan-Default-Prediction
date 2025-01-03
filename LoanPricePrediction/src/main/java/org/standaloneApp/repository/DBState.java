package org.standaloneApp.repository;
import java.io.File;
import java.sql.*;

import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;

public class DBState {

	DBConfig db=DBConfig.getInstance();
	protected Connection conn=db.getConn();
	protected PreparedStatement stmt=db.getStmt();
	protected CallableStatement cstmt=db.getCallable();
	protected ResultSet rs=db.getResultSet();	
}
