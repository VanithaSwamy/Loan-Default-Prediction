package org.standaloneApp.service;

import org.standaloneApp.repository.AdminRepository;
import org.standaloneApp.repository.AdminRepositoryImpl;

public class AdminServiceImpl implements AdminService {
	AdminRepository admin=new AdminRepositoryImpl();
	@Override
	public boolean adminLogin(String name,String password) {
		return admin.adminLogin(name,password);
	}

}
