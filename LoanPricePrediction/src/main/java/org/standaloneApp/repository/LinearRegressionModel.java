package org.standaloneApp.repository;

import java.util.ArrayList;
import java.util.List;

public class LinearRegressionModel extends DBState{
	public static void main(String x[]) {
		LinearRegressionModel lmodel=new LinearRegressionModel();
		System.out.println("Linear Regression Model to predict for Loan Amount");
		float loanamt=30000;
		
		try {
			float y = (lmodel.getMValue() * loanamt) + lmodel.getBValue();
			System.out.println("The value of y "+y);
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	public float getMeanLoanAmt() {
		try {
			//Find average of single Borrower
			stmt=conn.prepareStatement("select avg(loan_amt) from demoBorr");
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
	
	public int getMeanDefaultY() {
		try {
			//Find average of single Borrower
			stmt=conn.prepareStatement("select avg(defaultY) from demoBorr");
			rs=stmt.executeQuery();
			if(rs.next())
			{
				return rs.getInt(1);
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
	
	public List<Float> getDeviationOfLoanAmt(){
		List<Float> devlamt=new ArrayList<>();
		try {
			float meanLoanAmt=getMeanLoanAmt();
			System.out.println("The Mean of Loan Amount :"+meanLoanAmt);
			
			stmt=conn.prepareStatement("Select loan_amt from demoBorr");
			rs=stmt.executeQuery();
			
			while(rs.next()) {
				float lamt=rs.getFloat(1);	
				float dev=lamt-meanLoanAmt;
				devlamt.add(dev);
			}
			return devlamt;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	public List<Float> getDeviationOfDefaultY(){
		List<Float> devDefaultY=new ArrayList<>();
		try {
			float meanDefaultY=getMeanDefaultY();
			System.out.println("The Mean of Y "+meanDefaultY);
			
			stmt=conn.prepareStatement("Select defaultY from demoBorr");
			rs=stmt.executeQuery();
			
			while(rs.next()) {
				int defaultY=rs.getInt(1);
				
				float dev=defaultY-meanDefaultY;
				devDefaultY.add(dev);
			}
			
			return devDefaultY;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Float> getProductOfXY(){
		List<Float> prodList=new ArrayList<>();
		try {
			List<Float> devOfX=getDeviationOfLoanAmt();
			List<Float> devOfY=getDeviationOfDefaultY();
			
			if(devOfX.size() != devOfY.size()) {
				 System.out.println("Error: Deviation lists size mismatch.");
		         return prodList;
			}
			for(int i=0;i<devOfX.size();i++) {
				float prodXY=devOfX.get(i) * devOfY.get(i);
				prodList.add(prodXY);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prodList;
	}
	
	public float getSumOfProd() {
		List<Float> list=getProductOfXY();
		
		float result=0;
		
		for(Float lst:list) {
			result+=lst;
		}
		
		return result;
	}
	
	public float getSquareOfDevX(){
		float sumOfSquares = 0;
		try {
			
			List<Float> sqDevX=getDeviationOfLoanAmt();
			
			for(Float dev : sqDevX) {
				sumOfSquares+=Math.pow(dev,2);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return sumOfSquares;
	}
	
	public float getMValue() {
		float sumOfProdXY=getSumOfProd();
		float sumOfDevX=getSquareOfDevX();
		
		float m=sumOfProdXY/sumOfDevX;
		
		System.out.println("The m value is "+m);
		return m;
	}
	
	public float getBValue() {
		float meanY=getMeanDefaultY();
	
		float b=meanY - (getMValue() * getMeanLoanAmt());
		System.out.println("The b value is "+b);
		return b;
	}
}
