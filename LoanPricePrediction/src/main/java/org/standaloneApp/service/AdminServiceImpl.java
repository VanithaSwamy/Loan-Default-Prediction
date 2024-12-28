package org.standaloneApp.service;

import org.standaloneApp.repository.AdminRepository;
import org.standaloneApp.repository.AdminRepositoryImpl;
import java.util.*;

public class AdminServiceImpl implements AdminService {
	AdminRepository admin=new AdminRepositoryImpl();
	
	@Override
	public boolean adminLogin(String name,String password) {
		return admin.adminLogin(name,password);
	}
	@Override
	public Optional<Map<Integer, String>> getLoanType() {
		return admin.getLoanType();
	}
	@Override
	public boolean updateLoanName(String currName, String newName) {
		return admin.updateLoanName(currName, newName);
	}
	@Override
	public boolean isAddNewLoanType(String newName) {
		return admin.isAddNewLoanType(newName);
	}
	@Override
	public boolean deleteLoanName(String currName) {
		return admin.deleteLoanName(currName);
	}
}
