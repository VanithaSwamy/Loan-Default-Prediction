package org.standaloneApp.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoanRepositoryImpl extends DBState implements LoanRepository {
	@Override
	public Optional<Map<Integer,String>> getLoanType() {
		Map<Integer,String> loanTypes = new HashMap<>();//to store loan type details

		try {
				stmt = conn.prepareStatement(Query.getLoanType);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					loanTypes.put(rs.getInt(1),rs.getString(2));//to add in map
				}
				return loanTypes.isEmpty()? Optional.empty():Optional.of(loanTypes);
		}catch(Exception ex) {
			System.out.println("Error in GetLoanType :"+ex);
			return Optional.empty();//if an error occur 
		}
	}
	
	//to fetch loan id via name
	@Override
	public int getLoanId(String oldName) {
		try {
				stmt = conn.prepareStatement(Query.getLoanTypeId);
				stmt.setString(1, oldName);
				rs = stmt.executeQuery();
				
				if(rs.next()) 
					return rs.getInt(1); //if id found
				else 
					return -1;//if id not found
				
		}catch(Exception ex) {
			System.out.println("Error in GetLoanId: "+ex);
			return -1;
		}
		
	}

	@Override
	public boolean updateLoanName(String currName,String newName) {
		try {
				int loanId = this.getLoanId(currName);
				if(loanId !=-1) {
					stmt = conn.prepareStatement(Query.updateLoanTypeName);
					stmt.setString(1, newName);
					stmt.setInt(2, loanId);
					int val = stmt.executeUpdate();
					
					return val > 0 ? true : false;
				}
				else
					return false;
		}catch(Exception ex) {
			System.out.println("Error in Update LoanName: "+ex);
			return false;
		}
	}

	@Override
	public boolean isAddNewLoanType(String newName) {
		try {
				stmt = conn.prepareStatement(Query.setNewLoanType);
				stmt.setString(1, newName);
				int val = stmt.executeUpdate();
				return val > 0 ? true : false;
		}catch(Exception ex) {
			System.out.println("Error in setNewLoanName: "+ex);
			return false;
		}
	}

	@Override
	public boolean deleteLoanName(String currName) {
		try {
			int loanID = this.getLoanId(currName);
			if(loanID !=-1) {
				stmt = conn.prepareStatement(Query.deleteLoanTypeName);
				stmt.setInt(1, loanID);
				int val = stmt.executeUpdate();
				return val > 0 ? true : false;
			}else
				return false;
			
		}catch(Exception ex) {
			System.out.println("Error in DeleteLoanName: "+ex);
			return false;
		}

	}

}
