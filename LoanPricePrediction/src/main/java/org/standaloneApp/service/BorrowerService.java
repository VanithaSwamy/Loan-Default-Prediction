package org.standaloneApp.service;

import org.standaloneApp.model.BorrowerModel;

public interface BorrowerService{

	public boolean isAddNewBorrower(BorrowerModel model);
	public int getBorrowerId(String idproof);
	public boolean deleteBorrowerById(String idproof);
}
