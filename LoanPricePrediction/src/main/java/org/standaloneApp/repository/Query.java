package org.standaloneApp.repository;

public class Query {
	
	public static String addBorrower="insert into borrower values(0,?,?,?,?,?)";
	public static String getId="select borrower_id from borrower where id_proof=?";
	public static String deleteBorrowerById="delete from borrower where borrower_id=?"; 
	
	public static String getBorrowerRecord ="select * from borrower";
	public static String getBorrwerIdByNameIDProof ="select borrower_id from borrower where name=? && id_proof =?";
	public static String setBorrwerEmailById = "update borrower set email =? where borrower_id=?;";
	public static String getAge="SELECT TIMESTAMPDIFF(YEAR,dob, CURDATE()) as age from borrower where borrower_id=?;";
	public static String updateBorrowerNewName1 = "update borrower set name =? where borrower_id = ?;";
	public static String updateBorrowerBdate ="update borrower set dob = ? where borrower_id = ?;";
	public static String updateBorrowerContNumb = "update borrower set contact_number = ? where borrower_id=?;";
	
	public static String getAdmincredentials="select * from admin where name=? and password=?;";
	public static String getBorrowerDetail = "Select * from borrower where name = ? AND id_proof=?";
	
	//loan type
	public static String getLoanType = "select * from loan_type;";//to list all loan details
	public static String setNewLoanType = "insert into loan_type values(0,?);";
	public static String getLoanTypeId = " select loan_type_id from loan_type where type_name =?;";//to get loan id
	public static String updateLoanTypeName = "update loan_type set type_name = ? where loan_type_id = ?;";//to update name via id
	public static String deleteLoanTypeName = "delete from loan_type where loan_type_id = ? ;";//to delete laontype
	
	public static String addIncome="insert into income values(0,?,?,?);";
	public static String addCredit="insert into credit_score values(0,?,?);";
	public static String addLoanAmt="INSERT INTO loan_amt (loan_id, borrower_id, loan_type_id, amount) VALUES (0, ?, ?, ?);";	
	
	public static String addBorrowerLoanJoin="call addBorrowerLoanJoin(?,?,?)";
}
