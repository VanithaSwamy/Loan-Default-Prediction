package LinearRegressionModel;

import org.standaloneApp.repository.DBState;

public class Get_Value_Of_M_RepositoryImpl extends DBState implements Get_Value_Of_M_Repository{
	Sum_Of_Product_Of_Dev_of_X_Y_Repository sumOfXYRepo =new Sum_Of_Product_Of_Dev_of_X_Y_RepositoryImpl();
	
	Sum_of_Square_of_Deviation_Of_X_Repository sumSqXRepo=new Sum_of_Square_of_Deviation_Of_X_RepositoryImpl();
	
	@Override
	public float getMofIncome() {
		float sumOfProdXY=sumOfXYRepo.getSumOfDevIncomeAndStatus();
		float sumOfDevX=sumSqXRepo.sumOfProductOfDeviationOfIncome();
		
		float m=sumOfProdXY/sumOfDevX;
		
//		System.out.println("The m value is "+m);
		return m;
	}

	@Override
	public float getMofLoanAmt() {
		float sumOfProdXY=sumOfXYRepo.getSumOfDevLoanAmtAndStatus();
		float sumOfDevX=sumSqXRepo.sumOfProductOfDeviationOfLoanAmt();
		
		float m=sumOfProdXY/sumOfDevX;
		
//		System.out.println("The m value is "+m);
		return m;
	}

	@Override
	public float getMofCredScore() {
		float sumOfProdXY=sumOfXYRepo.getSumOfDevCredScoreAndStatus();
		float sumOfDevX=sumSqXRepo.sumOfProductOfDeviationOfCredScore();
		
		float m=sumOfProdXY/sumOfDevX;
		
//		System.out.println("The m value is "+m);
		return m;
	}

	@Override
	public float getMofAge() {
		float sumOfProdXY=sumOfXYRepo.getSumOfDevAgeAndStatus();
		float sumOfDevX=sumSqXRepo.sumOfProductOfDeviationOfAge();
		
		float m=sumOfProdXY/sumOfDevX;
		
//		System.out.println("The m value is "+m);
		return m;
	}

}
