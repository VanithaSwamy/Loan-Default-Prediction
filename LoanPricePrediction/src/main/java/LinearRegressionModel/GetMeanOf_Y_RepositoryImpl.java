package LinearRegressionModel;

import org.standaloneApp.repository.DBState;

public class GetMeanOf_Y_RepositoryImpl extends DBState implements GetMeanOf_Y_Repository {

	@Override
	public float getMeanOfDefault() {
		try {
			//Find average of single Borrower
			stmt=conn.prepareStatement(QueryLR.AvgStatus);
			rs=stmt.executeQuery();
			if(rs.next())
			{
				return rs.getFloat(1);
			}
			else {
				return 0;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
