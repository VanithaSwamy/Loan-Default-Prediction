package LinearRegressionModel;

import java.util.List;

import org.standaloneApp.repository.DBState;

public class Sum_of_Square_of_Deviation_Of_X_RepositoryImpl extends DBState implements Sum_of_Square_of_Deviation_Of_X_Repository {
	DeviationOfX_Repository devXRepo=new DeviationOfX_RepositoryImpl();
	
	@Override
	public float sumOfProductOfDeviationOfIncome() {
		float sumOfSquares = 0;
		try {
			
			List<Float> sqDevX=devXRepo.getDeviationOfIncome();
			for(Float dev : sqDevX) {
				sumOfSquares+=Math.pow(dev,2);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return sumOfSquares;
	}

	@Override
	public float sumOfProductOfDeviationOfLoanAmt() {
		float sumOfSquares = 0;
		try {
			
			List<Float> sqDevX=devXRepo.getDeviationOfLoanAmt();
			
			for(Float dev : sqDevX) {
				sumOfSquares+=Math.pow(dev,2);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return sumOfSquares;
	}

	@Override
	public float sumOfProductOfDeviationOfCredScore() {
		float sumOfSquares = 0;
		try {
			
			List<Float> sqDevX=devXRepo.getDeviationOfCredScore();
			
			for(Float dev : sqDevX) {
				sumOfSquares+=Math.pow(dev,2);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return sumOfSquares;
	}

	@Override
	public float sumOfProductOfDeviationOfAge() {
		float sumOfSquares = 0;
		try {
			
			List<Float> sqDevX=devXRepo.getDeviationOfAge();
			
			for(Float dev : sqDevX) {
				sumOfSquares+=Math.pow(dev,2);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return sumOfSquares;
	}

}
