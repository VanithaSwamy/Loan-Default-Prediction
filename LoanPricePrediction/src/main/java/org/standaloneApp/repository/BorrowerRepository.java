package org.standaloneApp.repository;

import org.standaloneApp.model.BorrowerModel;

public interface BorrowerRepository {
	
	public boolean isAddNewBorrower(BorrowerModel model);
	public int getBorrowerId(String idproof);
	public boolean deleteBorrowerById(String idproof);
}
