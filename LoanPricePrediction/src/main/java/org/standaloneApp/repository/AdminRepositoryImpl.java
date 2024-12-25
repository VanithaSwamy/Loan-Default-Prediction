package org.standaloneApp.repository;


public class AdminRepositoryImpl extends DBState implements AdminRepository{

	@Override
	public boolean adminLogin(String name,String password) {
		
		try {
			stmt=conn.prepareStatement(Query.getAdmincredentials);
			stmt.setString(1,name);
			stmt.setString(2,password);
			
			rs = stmt.executeQuery();
			
			if(rs.next())
				return rs.getInt(1)>0;
			else
				return false;
		}
		catch(Exception ex)
		{
			System.out.println("Error is "+ex);
			return false;
		}
	}
}
