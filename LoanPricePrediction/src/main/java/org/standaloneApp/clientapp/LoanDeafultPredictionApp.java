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
			System.out.println("1. Add Borrower details \n2. Delete Borrower record \n3. Exit \nEnter choice : ");
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

					BorrowerModel model = new BorrowerModel(0, bnm, sqlDate, phno, em, idno);
					boolean ans = borrowerService.isAddNewBorrower(model);

					if (ans)
						System.out.println("Borrower registered successfully....");
					else
						System.out.println(" Registeration failed ");
				} catch (Exception e) {
					System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
				}

				break;

			case 2:
				sc.nextLine();
				System.out.println("Enter Id proof number to delete record");
				String idno = sc.nextLine();
				
				boolean ans=borrowerService.deleteBorrowerById(idno);
				if(ans)
					System.out.println("Borrower deleted successfully....");
				else
					System.out.println("Failed to delete borrower ");
				
				break;

			case 3:
				System.exit(0);

			default:
				System.out.println("Invalid choice ....");
			}
		} while (true);
	}

}
