package LinearRegressionModel;

import java.util.List;

import org.standaloneApp.repository.DBState;

public class Sum_Of_Product_Of_Dev_of_X_Y_RepositoryImpl extends DBState implements Sum_Of_Product_Of_Dev_of_X_Y_Repository {
	Product_Of_Dev_Xand_Y_Repository prodOfXY =new Product_Of_Dev_Xand_Y_RepositoryImpl();
	List<Float> list;
	
	@Override
	public float getSumOfDevIncomeAndStatus() {
		list=prodOfXY.productOfDevIncomeAndStatus();
		float result=0;
		for(Float lst:list) {
			result+=lst;
		}
		return result;
	}

	@Override
	public float getSumOfDevLoanAmtAndStatus() {
		list=prodOfXY.productOfDevLoanAmtAndStatus();
		float result=0;
		for(Float lst:list) {
			result+=lst;
		}
		return result;
	}

	@Override
	public float getSumOfDevAgeAndStatus() {
		list=prodOfXY.productOfDevAgeAndStatus();
		float result=0;
		for(Float lst:list) {
			result+=lst;
		}
		return result;
	}

	@Override
	public float getSumOfDevCredScoreAndStatus() {
		list=prodOfXY.productOfDevCredScoreAndStatus();
		float result=0;
		for(Float lst:list) {
			result+=lst;
		}
		return result;
	}

}
