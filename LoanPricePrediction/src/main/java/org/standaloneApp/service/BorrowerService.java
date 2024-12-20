package org.standaloneApp.service;

import org.standaloneApp.model.BorrowerModel;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
public interface BorrowerService{

	public boolean isAddNewBorrower(BorrowerModel model);
	public int getBorrowerId(String idproof);
	public boolean deleteBorrowerById(String idproof);
	
	public Optional<List<BorrowerModel>> getAllBorrowers();
	public boolean isUpdatePhoneNumb(String currBName,String idProof,String newBPhoneNumb);
	public boolean isUpdateEmailAdrs(String currBName,String idProof,String newEmailAdrs);
	
	//to check borrower present or not
	public boolean isBorrowerPresent(String currBName,String idProof);
	public boolean isUpdateNewName(String currBName,String idProof,String newName);
	public boolean isUpdateNewBDate(String currName,String idProof,Date sqlDate1);
}
