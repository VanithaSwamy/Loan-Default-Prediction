package org.standaloneApp.repository;

import org.standaloneApp.model.BorrowerModel;

public class BorrowerRepositoryImpl extends DBState implements BorrowerRepository {

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

}
