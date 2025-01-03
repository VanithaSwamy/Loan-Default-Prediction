package org.standaloneApp.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.standaloneApp.model.BorrowerModel;
import org.standaloneApp.model.CreditModel;
import org.standaloneApp.model.IncomeModel;
import org.standaloneApp.model.LoanModel;
import org.standaloneApp.repository.BorrowerRepositoryImpl;
import org.standaloneApp.repository.BorrowerRepository;

public class BorrowerServiceImpl implements BorrowerService {
	BorrowerRepository user=new BorrowerRepositoryImpl();
	
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

	@Override
	public boolean isUpdatePhoneNumb(String currBName, String idProof, String newBPhoneNumb) {
		return user.isUpdatePhoneNumb(currBName,idProof,newBPhoneNumb);
	}


	@Override
	public boolean isUpdateEmailAdrs(String currBName, String idProof, String newEmailAdrs) {
		return user.isUpdateEmailAdrs(currBName, idProof, newEmailAdrs);
	}

	@Override
	public Optional<List<BorrowerModel>> getAllBorrowers() {
		return user.getAllBorrowers();
	}

	@Override
	public boolean isBorrowerPresent(String currBName, String idProof) {
		return user.isBorrowerPresent(currBName, idProof);
	}

	@Override
	public boolean isUpdateNewName(String currBName,String idProof,String newName) {
		return user.isUpdateNewName(currBName,idProof,newName);
	}

	@Override
	public boolean isUpdateNewBDate(String currName, String idProof, Date sqlDate1) {
		return user.isUpdateNewBDate(currName,idProof,sqlDate1);
	}

	@Override
	public Optional<BorrowerModel> getBorrower(String currName, String idProof) {
		return user.getBorrower(currName, idProof);
	}

	@Override
	public Optional<List<String>> getLoanType() {
		return user.getLoanType();
	}

	@Override
	public boolean addIncome(IncomeModel model,int borrId) {
		return user.addIncome(model, borrId);
	}

	@Override
	public boolean addCredit(CreditModel model, int borrId) {
		return user.addCredit(model, borrId);
	}

	@Override
	public int getLoanTypeId(String loanType) {
		return user.getLoanTypeId(loanType);
	}

	@Override
	public boolean addLoanAmt(LoanModel model,int borrId,int loanTypeId) {
		return user.addLoanAmt(model, borrId, loanTypeId);
	}
	
}

