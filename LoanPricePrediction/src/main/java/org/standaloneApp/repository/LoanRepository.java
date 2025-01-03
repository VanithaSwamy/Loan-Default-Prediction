package org.standaloneApp.repository;
import java.util.*;

public interface LoanRepository {
	public Optional<Map<Integer,String>> getLoanType();//to get all avail loan types ADMIN
	
	public boolean isAddNewLoanType(String newName);//to insert new loan type     ADMIN
	public int getLoanId(String oldName);//to get loan id via loan name           ADMIN
	public boolean updateLoanName(String currName,String newName);//to update loan name
	public boolean deleteLoanName(String currName);//to delete loan name record       ADMIN
}
