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

}
