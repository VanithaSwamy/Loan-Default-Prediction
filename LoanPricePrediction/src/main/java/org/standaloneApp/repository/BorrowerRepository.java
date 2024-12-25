package org.standaloneApp.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.standaloneApp.model.BorrowerModel;

public interface BorrowerRepository {
	
	public boolean isAddNewBorrower(BorrowerModel model);
	public int getBorrowerId(String idproof);
	public boolean deleteBorrowerById(String idproof); 
	
	public Optional<List<BorrowerModel>> getAllBorrowers(); //to list all borrower thechaitu
	public int getBorrowerIdByNameIdProof(String currBName,String idProof); //to get borrower id thechaitu
	public boolean isUpdatePhoneNumb(String currBName,String idProof,String newBPhoneNumb);//to update phone number of borrower thechaitu
	public boolean isUpdateEmailAdrs(String currBName,String idProof,String newEmailAdrs); //thechaitu
	
	//to check borrower present or not
	public boolean isBorrowerPresent(String currBName,String idProof);
	public boolean isUpdateNewName(String currBName,String idProof,String newName); //to update new name
	public boolean isUpdateNewBDate(String currBName,String idProof,Date bdate);//to update new birthdate

	public Optional<BorrowerModel> getBorrower(String currName,String idProof);//to get single borrower
}
