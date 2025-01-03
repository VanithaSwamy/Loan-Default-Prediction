package LinearRegressionModel;

import org.standaloneApp.repository.DBState;

public class GetMeanOf_X_RepositoryImpl extends DBState implements GetMeanOf_X_Repository {

	@Override
	public double getMeanOfIncome() {
		try {
			//Find average of single Borrower
			stmt=conn.prepareStatement(QueryLR.AvgIncome);
			rs=stmt.executeQuery();
			if(rs.next())
			{
				return rs.getDouble(1);
			}
			else {
				return 0;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public double getMeanOfLoanAmt() {
		try {
			//Find average of single Borrower
			stmt=conn.prepareStatement(QueryLR.AvgLoanAmt);
			rs=stmt.executeQuery();
			if(rs.next())
			{
				return rs.getDouble(1);
			}
			else {
				return 0;
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public float getMeanOfAge() {
		try {
			//Find average of single Borrower
			stmt=conn.prepareStatement(QueryLR.AvgAge);
			rs=stmt.executeQuery();
			if(rs.next())
			{
				return rs.getFloat(1);
			}
			else {
				return 0;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public float getMeanOfCreditScore() {
		try {
			//Find average of single Borrower
			stmt=conn.prepareStatement(QueryLR.AvgCredScore);
			rs=stmt.executeQuery();
			if(rs.next())
			{
				return rs.getFloat(1);
			}
			else {
				return 0;
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
