package org.standaloneApp.repository;

public class Query {
	
	public static String addBorrower="insert into borrower values(0,?,?,?,?,?)";
	public static String getId="select borrower_id from borrower where id_proof=?";
	public static String deleteBorrowerById="delete from borrower where borrower_id=?"; 
	
}
