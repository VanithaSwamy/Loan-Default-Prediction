package org.standaloneApp.clientapp;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.*;

import org.standaloneApp.model.BorrowerModel;
import org.standaloneApp.service.BorrowerServiceImpl;

public class LoanDeafultPredictionApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		BorrowerServiceImpl borrowerService = new BorrowerServiceImpl();

		do {
			System.out.println("1. Add Borrower details \n2. Display Borrower details \n3. Update Borrower Details \n4. Delete Borrower record \n5. Exit \nEnter choice : ");
			int ch = sc.nextInt();

			switch (ch) {
			case 1:
				try {
					sc.nextLine();

					System.out.println("Enter Borrower name ");
					String bnm = sc.nextLine();

					System.out.println("Enter Date of birth in yyyy-MM-dd format ");
					String dob = sc.nextLine();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date utilDate = dateFormat.parse(dob);
					Date sqlDate = new Date(utilDate.getTime());

					System.out.println("Enter Contact number ");
					String phno = sc.nextLine();

					System.out.println("Enter Email ID ");
					String em = sc.nextLine();

					System.out.println("Enter Id proof number ");
					String idno = sc.nextLine();

					System.out.println(borrowerService.isAddNewBorrower(new BorrowerModel(0, bnm, sqlDate, phno, em, idno))? "Borrower registered successfully...." : " Registeration failed ");

				} catch (Exception e) {
					System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
				}
				break;

			case 2:
				//Display Borrower details
				
				break;
				
			case 3:
				//update
				break;
				
			case 4:
				sc.nextLine();
				System.out.println("Enter Id proof number to delete record");
				String idno = sc.nextLine();
				System.out.println(borrowerService.deleteBorrowerById(idno)?"Borrower deleted successfully....":"Failed to delete borrower ");
				break;
			
			case 5:
				System.exit(0);

			case 6:
				
				break;
			default:
				System.out.println("Invalid choice ....");
			}
		} while (true);
	}

}
