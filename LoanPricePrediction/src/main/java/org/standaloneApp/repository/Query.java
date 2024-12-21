package org.standaloneApp.repository;

public class Query {
	
	public static String addBorrower="insert into borrower values(0,?,?,?,?,?)";
	public static String getId="select borrower_id from borrower where id_proof=?";
	public static String deleteBorrowerById="delete from borrower where borrower_id=?"; 
	
	public static String getBorrowerRecord ="select * from borrower";
	public static String getBorrwerIdByNameIDProof ="select borrower_id from borrower where name=? && id_proof =?";
	public static String setBorrwerEmailById = "update borrower set email =? where borrower_id=?;";
	
	public static String updateBorrowerNewName1 = "update borrower set name =? where borrower_id = ?;";
	public static String updateBorrowerBdate ="update borrower set dob = ? where borrower_id = ?;";
	public static String updateBorrowerContNumb = "update borrower set contact_number = ? where borrower_id=?;";
}