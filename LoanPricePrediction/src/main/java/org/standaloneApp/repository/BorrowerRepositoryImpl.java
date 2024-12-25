package org.standaloneApp.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.standaloneApp.model.BorrowerModel;

public class BorrowerRepositoryImpl extends DBState implements BorrowerRepository {
	List<BorrowerModel> borrowerList = null;

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
		} catch (Exception e) {
			System.out.println("Error is " + e.getMessage());
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
//				BorrowerModel model = new BorrowerModel(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5),rs.getString(6));
//				borrowerList.add(model);
				borrowerList.add(new BorrowerModel(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4),
						rs.getString(5), rs.getString(6)));

			}
			if (borrowerList.isEmpty())
				throw new Exception("BorrowerMaster Empty Exception");
			else
				return Optional.ofNullable(borrowerList);

		} catch (Exception ex) {
			System.out.println("Error is " + ex.getMessage());
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
				stmt.setDate(1, bdate);
				stmt.setInt(2, borrowerId);
				int val  = stmt.executeUpdate();
				return val > 0 ? true: false;
			}else {
				return true;
			}	
		}catch(Exception ex) {
			System.out.println("Error message is: "+ex);
			return false;
		}
	}

	@Override
	public Optional<BorrowerModel> getBorrower(String currName, String idProof) {
		 try {
		        stmt = conn.prepareStatement("SELECT * FROM borrower WHERE name = ? AND id_proof = ?");
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
		    }
		    // Return empty Optional if no record found or in case of an error
		    return Optional.empty();
	}

}
