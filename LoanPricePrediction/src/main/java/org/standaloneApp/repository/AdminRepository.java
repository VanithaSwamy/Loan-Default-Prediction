package org.standaloneApp.repository;

import java.util.*;

import org.standaloneApp.model.LoanModel;
public interface AdminRepository {
	
	boolean adminLogin(String name,String password);
	public Optional<Map<Integer,String>> getLoanType();//to get all avail loan types
	public boolean isAddNewLoanType(String newName);//to insert new loan type
	public int getLoanId(String oldName);//to get loan id via loan name
	public boolean updateLoanName(String currName,String newName);//to update loan name
	public boolean deleteLoanName(String currName);//to delete loan name record
	
	public List<LoanModel> loanApprovalRecords();//approval loans
	public List<LoanModel> loanRejectedRecords();//rejected loans

}
