package org.standaloneApp.service;

import java.util.Map;
import java.util.Optional;

import org.standaloneApp.repository.AdminRepository;
import org.standaloneApp.repository.AdminRepositoryImpl;
import org.standaloneApp.repository.*;

public class AdminServiceImpl implements AdminService {
	AdminRepository admin=new AdminRepositoryImpl();
	LoanRepository loanDetails = new LoanRepositoryImpl();
	
	@Override
	public boolean adminLogin(String name,String password) {
		return admin.adminLogin(name,password);
	}
	@Override
	public Optional<Map<Integer, String>> getLoanType() {
		return loanDetails.getLoanType();
	}
	@Override
	public boolean updateLoanName(String currName, String newName) {
		return loanDetails.updateLoanName(currName, newName);
	}
	@Override
	public boolean isAddNewLoanType(String newName) {
		return loanDetails.isAddNewLoanType(newName);
	}
	@Override
	public boolean deleteLoanName(String currName) {
		return loanDetails.deleteLoanName(currName);
	}
	
}