package org.standaloneApp.service;

import org.standaloneApp.model.BorrowerModel;
import org.standaloneApp.repository.BorrowerRepositoryImpl;

public class BorrowerServiceImpl implements BorrowerService {
	BorrowerRepositoryImpl user=new BorrowerRepositoryImpl();
	
	@Override
	public boolean isAddNewBorrower(BorrowerModel model) {
		return user.isAddNewBorrower(model);
	}

	@Override
	public int getBorrowerId(String idproof) {
		return user.getBorrowerId(idproof);
	}

	@Override
	public boolean deleteBorrowerById(String idproof) {
		return user.deleteBorrowerById(idproof);
	}

}
