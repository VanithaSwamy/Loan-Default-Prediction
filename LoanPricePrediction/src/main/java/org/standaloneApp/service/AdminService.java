package org.standaloneApp.service;

import java.util.*;

import org.standaloneApp.model.LoanModel;

public interface AdminService {
	boolean adminLogin(String name,String password);
	
	Optional<Map<Integer,String>> getLoanType();
	public boolean updateLoanName(String currName,String newName);//to update loan name
	public boolean isAddNewLoanType(String newName);//to insert new loan type
	public boolean deleteLoanName(String currName);
	
	public List<LoanModel> loanApprovalRecords();//approval loans
	public List<LoanModel> loanRejectedRecords();//rejected loans
}
