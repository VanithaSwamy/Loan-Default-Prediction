package LinearRegressionModel;

import java.util.ArrayList;
import java.util.List;

import org.standaloneApp.repository.DBState;

public class Product_Of_Dev_Xand_Y_RepositoryImpl extends DBState implements Product_Of_Dev_Xand_Y_Repository {
	
	DeviationOfX_Repository devXRepo= new DeviationOfX_RepositoryImpl();
	DeviationOfY_Repository devYRepo=new DeviationOfY_RepositoryImpl();
	List<Float> prodListXY;
	
	@Override
	public List<Float> productOfDevIncomeAndStatus() {
		prodListXY=new ArrayList<>();
		try {
			List<Float> devOfX=devXRepo.getDeviationOfIncome();
			List<Float> devOfY=devYRepo.getDeviationOfStatus();
			
			if(devOfX.size() != devOfY.size()) {
				 System.out.println("Error: Deviation lists size mismatch.");
		         return prodListXY;
			}
			for(int i=0;i<devOfX.size();i++) {
				float prodXY=devOfX.get(i) * devOfY.get(i);
				prodListXY.add(prodXY);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prodListXY;
	}

	@Override
	public List<Float> productOfDevLoanAmtAndStatus() {
		prodListXY=new ArrayList<>();
		try {
			List<Float> devOfX=devXRepo.getDeviationOfLoanAmt();
			List<Float> devOfY=devYRepo.getDeviationOfStatus();
			
			if(devOfX.size() != devOfY.size()) {
				 System.out.println("Error: Deviation lists size mismatch.");
		         return prodListXY;
			}
			for(int i=0;i<devOfX.size();i++) {
				float prodXY=devOfX.get(i) * devOfY.get(i);
				prodListXY.add(prodXY);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prodListXY;
	}

	@Override
	public List<Float> productOfDevAgeAndStatus() {
		prodListXY=new ArrayList<>();
		try {
			List<Float> devOfX=devXRepo.getDeviationOfAge();
			List<Float> devOfY=devYRepo.getDeviationOfStatus();
			
			if(devOfX.size() != devOfY.size()) {
				 System.out.println("Error: Deviation lists size mismatch.");
		         return prodListXY;
			}
			for(int i=0;i<devOfX.size();i++) {
				float prodXY=devOfX.get(i) * devOfY.get(i);
				prodListXY.add(prodXY);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prodListXY;
	}

	@Override
	public List<Float> productOfDevCredScoreAndStatus() {
		prodListXY=new ArrayList<>();
		try {
			List<Float> devOfX=devXRepo.getDeviationOfCredScore();
			List<Float> devOfY=devYRepo.getDeviationOfStatus();
			
			if(devOfX.size() != devOfY.size()) {
				 System.out.println("Error: Deviation lists size mismatch.");
		         return prodListXY;
			}
			for(int i=0;i<devOfX.size();i++) {
				float prodXY=devOfX.get(i) * devOfY.get(i);
				prodListXY.add(prodXY);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prodListXY;
	}

}
