package LinearRegressionModel;

import java.util.*;

import org.standaloneApp.repository.DBState;

public class DeviationOfX_RepositoryImpl extends DBState implements DeviationOfX_Repository {
	
	GetMeanOf_X_Repository getMeanOfX=new GetMeanOf_X_RepositoryImpl();
	List<Float> deviations ;
	
	@Override
	public List<Float> getDeviationOfIncome() {
		deviations=new ArrayList<>();
		try {
			float meanIncome=getMeanOfX.getMeanOfIncome();
			
//			System.out.println("The Mean of Income :"+meanIncome);
			
			stmt=conn.prepareStatement(QueryLR.getListOfIncome);
			rs=stmt.executeQuery();
			
			while(rs.next()) {
				float income=rs.getFloat(1);	
				float dev=income-meanIncome;
				deviations.add(dev);
			}
			return deviations;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public List<Float> getDeviationOfLoanAmt() {
		deviations=new ArrayList<>();
		try {
			float meanLoanAmt=getMeanOfX.getMeanOfLoanAmt();
			
//			System.out.println("The Mean of Loan Amount :"+meanLoanAmt);
			
			stmt=conn.prepareStatement(QueryLR.getListOfLoanAmt);
			rs=stmt.executeQuery();
			
			while(rs.next()) {
				float amt=rs.getFloat(1);	
				float dev=amt-meanLoanAmt;
				deviations.add(dev);
			}
			return deviations;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public List<Float> getDeviationOfCredScore() {
		deviations=new ArrayList<>();
		try {
			float meanCredScore=getMeanOfX.getMeanOfCreditScore();
			
//			System.out.println("The Mean of Credit Score :"+meanCredScore);
			
			stmt=conn.prepareStatement(QueryLR.getListOfLoanAmt);
			rs=stmt.executeQuery();
			
			while(rs.next()) {
				float credScore=rs.getFloat(1);	
				float dev=credScore-meanCredScore;
				deviations.add(dev);
			}
			return deviations;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public List<Float> getDeviationOfAge() {
		deviations=new ArrayList<>();
		try {
			float meanAge=getMeanOfX.getMeanOfAge();
			
//			System.out.println("The Mean of Age :"+meanAge);
			
			stmt=conn.prepareStatement(QueryLR.getListOfAge);
			rs=stmt.executeQuery();
			
			while(rs.next()) {
				float age=rs.getFloat(1);	
				float dev=age-meanAge;
				deviations.add(dev);
			}
			return deviations;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

}
