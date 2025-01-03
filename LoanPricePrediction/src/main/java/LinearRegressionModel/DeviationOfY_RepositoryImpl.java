package LinearRegressionModel;

import java.util.ArrayList;
import java.util.List;

import org.standaloneApp.repository.DBState;

public class DeviationOfY_RepositoryImpl extends DBState implements DeviationOfY_Repository {
	
	GetMeanOf_Y_Repository getMeanOfY=new GetMeanOf_Y_RepositoryImpl();
	
	@Override
	public List<Float> getDeviationOfStatus() {
		List<Float> deviations=new ArrayList<>();
		try {
			float meanStatus=getMeanOfY.getMeanOfStatus();
			
//			System.out.println("The Mean of Status :"+meanStatus);
			
			stmt=conn.prepareStatement(QueryLR.getListStatus);
			rs=stmt.executeQuery();
			
			while(rs.next()) {
				float status=rs.getFloat(1);	
				float dev=status-meanStatus;
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
