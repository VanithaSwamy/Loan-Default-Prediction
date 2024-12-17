package org.standaloneApp.repository;
import java.sql.*;

public class DBState {

	DBConfig db=DBConfig.getInstance();
	protected Connection conn=db.getConn();
	protected PreparedStatement stmt=db.getStmt();
	protected CallableStatement cstmt=db.getCallable();
	protected ResultSet rs=db.getResultSet();
	
}
