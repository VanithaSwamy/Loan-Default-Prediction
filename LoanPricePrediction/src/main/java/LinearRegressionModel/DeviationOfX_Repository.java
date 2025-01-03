package LinearRegressionModel;

import java.util.List;

public interface DeviationOfX_Repository {
	
	public List<Float> getDeviationOfIncome();
	public List<Float> getDeviationOfLoanAmt();
	public List<Float> getDeviationOfCredScore();
	public List<Float> getDeviationOfAge();
}
