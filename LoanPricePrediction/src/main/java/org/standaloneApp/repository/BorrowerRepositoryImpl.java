package org.standaloneApp.repository;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.standaloneApp.model.BorrowerModel;
import org.standaloneApp.model.CreditModel;
import org.standaloneApp.model.IncomeModel;
import org.standaloneApp.model.LoanModel;

public class BorrowerRepositoryImpl extends DBState implements BorrowerRepository {
	List<BorrowerModel> borrowerList = null;
	static Logger logger = Logger.getLogger(BorrowerRepositoryImpl.class);
	static {
		PropertyConfigurator.configure(new File("").getAbsolutePath()+"\\src\\main\\resources\\application.properties");
		logger.setLevel(Level.ALL);
	}
	@Override
	public boolean isAddNewBorrower(BorrowerModel model) {
		try {
			stmt = conn.prepareStatement(Query.addBorrower);
			stmt.setString(1, model.getName());
			stmt.setDate(2, model.getDob());
			stmt.setString(3, model.getPhno()); 
			stmt.setString(4, model.getEmail());
			stmt.setString(5, model.getId_proof());

			int value = stmt.executeUpdate();
			return value > 0 ? true : false;
		} catch (Exception ex) {
			System.out.println("Error is " + ex.getMessage());
			logger.fatal("Exception in to AddNewBorrower :"+ex);
			return false;
		}
	}

	@Override
	public int getBorrowerId(String idproof) {
		try {
			stmt = conn.prepareStatement(Query.getId);
			stmt.setString(1, idproof);
			rs = stmt.executeQuery();

			if (rs.next())
				return rs.getInt(1);
			else
				return -1;
		} catch (Exception e) {
			System.out.println("Error is " + e.getMessage());
			logger.fatal("Exception in to GetBorrowerId :"+e);
			return -1;
		}
	}

	@Override
	public boolean deleteBorrowerById(String idproof) {
		try {
			int bid = this.getBorrowerId(idproof);
			if (bid != -1) {
				stmt = conn.prepareStatement(Query.deleteBorrowerById);
				stmt.setInt(1, bid);
				int value = stmt.executeUpdate();
				return value > 0 ? true : false;
			} else
				return false;
		} catch (Exception e) {
			System.out.println("Error is " + e.getMessage());
			logger.fatal("Exception in to DeleteBorrowerById :"+e);
			
			return false;
		}
	}

	@Override
	public Optional<List<BorrowerModel>> getAllBorrowers() {
		try {
			borrowerList = new ArrayList<BorrowerModel>();
			stmt = conn.prepareStatement(Query.getBorrowerRecord);
			rs = stmt.executeQuery();

			while (rs.next()) {
				borrowerList.add(new BorrowerModel(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4),
						rs.getString(5), rs.getString(6)));

			}
			if (borrowerList.isEmpty())
				throw new Exception("BorrowerMaster Empty Exception");
			else
				return Optional.ofNullable(borrowerList);

		} catch (Exception ex) {
			System.out.println("Error is " + ex.getMessage());
			logger.fatal("Exception in to GetAllBorrowers :"+ex);
			return Optional.empty();
		}
	}

	// to get borrower id by its name and idproof
	@Override
	public int getBorrowerIdByNameIdProof(String currBName, String idProof) {
		try {
			stmt = conn.prepareStatement(Query.getBorrwerIdByNameIDProof);
			stmt.setString(1, currBName);
			stmt.setString(2, idProof);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return -1;
			}
		} catch (Exception ex) {
			System.out.println("Error message in getBorrowerIdByName :" + ex);
			logger.fatal("Exception in to GetBorrowerIdByNameIdProof :"+ex);
			return -1;
		}
	}

	// to update phone number of borrower using borrower id
	@Override
	public boolean isUpdatePhoneNumb(String currBName, String idProof, String newBPhoneNumb) {
		try {
			int borrowerId = this.getBorrowerIdByNameIdProof(currBName,idProof);
			if(borrowerId != -1) {
				stmt = conn.prepareStatement(Query.updateBorrowerContNumb);
				stmt.setString(1,newBPhoneNumb);
				stmt.setInt(2, borrowerId);
				int val = stmt.executeUpdate();
				return val > 0 ? true:false;
			}else {
				return false;
			}
		}catch(Exception ex) {
			System.out.println("Exception is: "+ex);
			logger.fatal("Exception in to IsUpdatePhoneNumb :"+ex);
			
			return false;
		}
	}

	//to update email adrs of borrower
	@Override
	public boolean isUpdateEmailAdrs(String currBName, String idProof, String newEmailAdrs) {
		try {
			int borrowerId = this.getBorrowerIdByNameIdProof(currBName, idProof);
			if(borrowerId != -1)
			{
				stmt = conn.prepareStatement(Query.setBorrwerEmailById);
				stmt.setString(1, newEmailAdrs);
				stmt.setInt(2, borrowerId);
				int val = stmt.executeUpdate();
				return val > 0 ? true: false;
			}else {
				return false;
			}
		}catch(Exception ex) {
			System.out.println("Error message isUpdateEmailAdrs: "+ex);
			logger.fatal("Exception in to IsUpdateEmailAdrs :"+ex);
			
			return false;
		}
	}

	//to check borrower present or not in db
	@Override
	public boolean isBorrowerPresent(String currBName, String idProof) {
		try {
			stmt = conn.prepareStatement(Query.getBorrwerIdByNameIDProof);
			stmt.setString(1, currBName);
			stmt.setString(2, idProof);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return true; //if record present
			}else {
				return false;
			}
		}catch(Exception ex) {
			logger.fatal("Exception in to IsBorrowerPresent :"+ex);
			return false;
		}
	}

	@Override
	public boolean isUpdateNewName(String currBName,String idProof,String newName) {
			try {
				int borrowerId = this.getBorrowerIdByNameIdProof(currBName, idProof);
				if(borrowerId !=-1)
				{
					stmt = conn.prepareStatement(Query.updateBorrowerNewName1);
					stmt.setString(1, newName);
					stmt.setInt(2, borrowerId);
					int val = stmt.executeUpdate();
					return val > 0 ? true: false;
				}else {
					return false;
				}	
		}catch(Exception ex) {
			System.out.println("Error message is: "+ex);
			logger.fatal("Exception in to IsUpdateNewName :"+ex);
			return false;
		}
	}

	@Override
	public boolean isUpdateNewBDate(String currBName, String idProof, Date bdate) {
		try {
			int borrowerId = this.getBorrowerIdByNameIdProof(currBName, idProof);
			if(borrowerId != -1)
			{
				stmt = conn.prepareStatement(Query.updateBorrowerBdate);	
				stmt.setDate(1,bdate);
				stmt.setInt(2,borrowerId);
				int val  = stmt.executeUpdate();
				return val > 0 ? true: false;
			}else {
				return false;
			}	
		}catch(Exception ex) {
			System.out.println("Error message is: "+ex);
			logger.fatal("Exception in to Update New BirthDate :"+ex);
			return false;
		}
	}

	@Override
	public Optional<BorrowerModel> getBorrower(String currName, String idProof) {
		 try {
		        stmt = conn.prepareStatement(Query.getBorrowerDetail);
		        stmt.setString(1, currName);
		        stmt.setString(2, idProof);
		        rs = stmt.executeQuery();

		        if (rs.next()) {
		            BorrowerModel borrower = new BorrowerModel();
		            borrower.setBid(rs.getInt(1));
		            borrower.setName(rs.getString(2));
		            
		            // Retrieve and set the date_of_birth
		            java.sql.Date dobSql = rs.getDate(3);
		            if (dobSql != null) {
		                borrower.setDob(dobSql); 
		            }
//		            borrower.setDob(rs.getString(3));
		            borrower.setPhno(rs.getString(4));
		            borrower.setEmail(rs.getString(5));
		            borrower.setId_proof(rs.getString(6));
		            
		            return Optional.of(borrower);
		        }
		    } catch (Exception ex) {
		        System.out.println("Error: " + ex.getMessage());
		        // Return empty Optional if no record found or in case of an error
		        logger.fatal("Exception in to Get Borrower :"+ex);
		        return Optional.empty();
		    }
		 return Optional.empty();
		   
	}

	@Override
	public Optional<List<String>> getLoanType() {
		try {
			List<String> loanTypeList=new ArrayList<>();
			stmt=conn.prepareStatement(Query.getLoanType);
			rs=stmt.executeQuery();
			while(rs.next()) {
				loanTypeList.add(rs.getString(2));
			}
			if(loanTypeList.isEmpty())
				throw new Exception("No List of loan type");
			else
				return Optional.ofNullable(loanTypeList);	
		}
		catch(Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			logger.fatal("Exception in to Get Loan Type :"+ex);
			
			return Optional.empty();
		}
		
	}
	@Override
	public boolean addIncome(IncomeModel model,int borrId) {
		try {
			stmt=conn.prepareStatement(Query.addIncome);
			stmt.setInt(1,borrId);
			stmt.setFloat(2,model.getIncome());
			stmt.setString(3, model.getIncome_source());
			int value = stmt.executeUpdate();
			return value > 0 ? true : false;
		}
		catch(Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			logger.fatal("Exception in to Add Income :"+ex);
			return false;
		}	
	}
	@Override
	public boolean addCredit(CreditModel model, int borrId) {
		try {
			stmt=conn.prepareStatement(Query.addCredit);
			stmt.setInt(1,borrId);
			stmt.setInt(2,model.getCred_score());
			int value = stmt.executeUpdate();
			return value > 0 ? true : false;			
		}
		catch(Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			logger.fatal("Exception in to Add Credit :"+ex);
			return false;
		}
	}
	@Override
	public int getLoanTypeId(String loanType) {
		try {
			stmt=conn.prepareStatement(Query.getLoanTypeId);
			stmt.setString(1,loanType);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return -1;
			}	
		}
		catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
			logger.fatal("Exception in to Get Loan TypeId :"+e);
			return -1;
		}
	}
	@Override
	public boolean addLoanAmt(LoanModel model,int borrId,int loanTypeId) {
		try {
			stmt=conn.prepareStatement(Query.addLoanAmt);
			stmt.setInt(1,borrId);
			stmt.setInt(2, loanTypeId);
			stmt.setFloat(3,model.getLoan_amt());
			int value = stmt.executeUpdate();
			return value > 0 ? true : false;
		}
		catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
			logger.fatal("Exception in to Add Loan Amount :"+e);
			return false;
		}
	}

	@Override
	public int getAgeById(String idproof) {
		try {
			int id=this.getBorrowerId(idproof);
			stmt = conn.prepareStatement(Query.getAge);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next())
				return rs.getInt(1);
			else
				return -1;
		} catch (Exception e) {
			System.out.println("Error is " + e.getMessage());
			logger.fatal("Exception in to Get Age :"+e);
			return -1;
		}
	}

	@Override
	public boolean addBorrowerLoanJoin(int borrId, int status, String reason) {
		try {
			stmt = conn.prepareStatement(Query.addBorrowerLoanJoin);
			stmt.setInt(1, borrId);
			stmt.setInt(2, status);
			stmt.setString(3, reason);
			int value = stmt.executeUpdate();
			return value > 0 ? true : false;
			
		}catch(Exception ex) {
			logger.fatal("Exception unable to add :"+ex);
			return false;
		}
	}
}
