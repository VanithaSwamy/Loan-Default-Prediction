package org.standaloneApp.service;

import java.util.Map;
import java.util.Optional;

import org.standaloneApp.repository.AdminRepository;
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

}