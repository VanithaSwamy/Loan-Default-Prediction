package org.standaloneApp.service;
import java.util.*;
public interface AdminService {
	boolean adminLogin(String name,String password);
	
	Optional<Map<Integer,String>> getLoanType();
	public boolean updateLoanName(String currName,String newName);//to update loan name
	public boolean isAddNewLoanType(String newName);//to insert new loan type
	public boolean deleteLoanName(String currName);
}
