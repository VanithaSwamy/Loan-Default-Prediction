package LinearRegressionModel;

import org.standaloneApp.repository.DBState;

public class Get_Value_of_B_Intercept_RepositoryImpl extends DBState implements Get_Value_of_B_Intercept_Repository{
	GetMeanOf_Y_Repository getMeanOfY=new GetMeanOf_Y_RepositoryImpl();
	GetMeanOf_X_Repository getMeanOfX=new GetMeanOf_X_RepositoryImpl();
	Get_Value_Of_M_Repository getMVal=new Get_Value_Of_M_RepositoryImpl();
	@Override
	public float valueOfBIncomeIntercept() {
		float meanY=getMeanOfY.getMeanOfStatus();
		float b=meanY - (getMVal.getMofIncome() * getMeanOfX.getMeanOfIncome());
//		System.out.println("The b value is "+b);
		return b;
	}

	@Override
	public float valueOfBLoanAmtIntercept() {
		float meanY=getMeanOfY.getMeanOfStatus();
		float b=meanY - (getMVal.getMofLoanAmt() * getMeanOfX.getMeanOfLoanAmt());
//		System.out.println("The b value is "+b);
		return b;
	}

	@Override
	public float valueOfBCredScoreIntercept() {
		float meanY=getMeanOfY.getMeanOfStatus();
		float b=meanY - (getMVal.getMofCredScore() * getMeanOfX.getMeanOfCreditScore());
//		System.out.println("The b value is "+b);
		return b;
	}

	@Override
	public float valueOfBAgeIntercept() {
		float meanY=getMeanOfY.getMeanOfStatus();
		float b=meanY - (getMVal.getMofAge() * getMeanOfX.getMeanOfAge());
//		System.out.println("The b value is "+b);
		return b;
	}

}
