package org.standaloneApp.service;

import org.standaloneApp.model.BorrowerModel;
import org.standaloneApp.model.CreditModel;
import org.standaloneApp.model.IncomeModel;
import org.standaloneApp.model.LoanModel;

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
	
	public Optional<BorrowerModel> getBorrower(String currName,String idProof);
	
	public Optional<List<String>> getLoanType();
	public boolean addIncome(IncomeModel model,int borrId);
	public boolean addCredit(CreditModel model,int borrId);
	public int getLoanTypeId(String loanType);
	public boolean addLoanAmt(LoanModel model,int borrId,int loanTypeId);
}
