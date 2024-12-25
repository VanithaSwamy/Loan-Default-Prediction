package org.standaloneApp.clientapp;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.*;

import org.standaloneApp.model.BorrowerModel;
import org.standaloneApp.service.*;

public class LoanDeafultPredictionApp {
	private static AdminService admin = new AdminServiceImpl();
	private static BorrowerService borrowerService = new BorrowerServiceImpl();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		boolean running = true;

		while (running) {
			System.out.println("Welcome to the Portal");
			System.out.println("1: User");
			System.out.println("2: Admin");
			System.out.println("3: Exit");
			System.out.print("Enter your choice: ");

			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				userLogin(sc);
				break;

			case 2:
				adminLogin(sc);
				break;

			case 3:
				System.out.println("Exiting the application. Goodbye!");
				running = false; // Exit the loop
				break;

			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
		sc.close();
	}

	private static void userLogin(Scanner sc) {

		sc.nextLine(); // Clear scanner buffer
		System.out.println("---- User Login ----");
		System.out.print("Enter username: ");
		String borrName = sc.nextLine();
		System.out.print("Enter Id proof number: ");
		String idno = sc.nextLine();

		if (borrowerService.isBorrowerPresent(borrName, idno)) {
			boolean running = true;

			do {
				System.out.println(
						"1: Add Borrower details \n2: Update Borrower Details \n3: Delete Borrower record \n4: Add Data for Loan Evaluation \n5. Exit \nEnter choice : ");
				int ch = sc.nextInt();

				switch (ch) {
				case 1:
					Function.addBorrower(borrowerService);
					break;
					
				case 2:
					Function.updateBorrower(borrowerService);
					break;
					
				case 3:
					Function.deleteBorrower(borrowerService);
					break;

				case 4:
					Function.DataEvaluation(borrowerService);
					break;

				case 5:
					System.out.println("Exiting the program...");
					running = false; // Stop the loop
					break;

				default:
					System.out.println("Invalid choice ....");
					break;
				}
			} while (running);
		} else {
			System.out.println("Invalid credentials. Please try again.");
			System.out.println("Do You Want To Register " + borrName + " Borrower Details (Yes/No) ?");
			String msg = sc.nextLine();

			if (msg.equalsIgnoreCase("Yes")) {
				try {

					System.out.println("Enter Date of birth in yyyy-MM-dd format ");
					String dob = sc.nextLine();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date utilDate = dateFormat.parse(dob);
					Date sqlDate = new Date(utilDate.getTime());

					System.out.println("Enter Contact number ");
					String phno = sc.nextLine();

					System.out.println("Enter Email ID ");
					String em = sc.nextLine();

					System.out.println(
							borrowerService.isAddNewBorrower(new BorrowerModel(0, borrName, sqlDate, phno, em, idno))
									? "Borrower registered successfully...."
									: " Registeration failed ");
				} catch (Exception e) {
					System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
				}
			}
			else {
				System.out.println("Thank You For Response");
			}
		}

	}

	private static void adminLogin(Scanner sc) {
		sc.nextLine(); // Clear scanner buffer
		System.out.println("---- Admin Login ----");
		System.out.print("Enter admin username: ");
		String adminUsername = sc.nextLine();
		System.out.print("Enter admin password: ");
		String adminPassword = sc.nextLine();

		if (admin.adminLogin(adminUsername, adminPassword)) {
			boolean running = true;

			while (running) {
				System.out.println("\n--- Welcome to Admin Portal ---");
				System.out.println("1: View All Borrowers");
				System.out.println("2: Delete Borrower");
				System.out.println("3: Add Loan Types");
				System.out.println("4: Check Evaluation for Bulk Data");
				System.out.println("5: Exit");
				System.out.print("Enter your choice: ");

				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					Function.displayBorrower(borrowerService);
					break;

				case 2:
					Function.deleteBorrower(borrowerService);
					break;

				case 3:
					//add loan types
					break;

				case 4:
					//check evaluation for bulk data
					break;

				case 5:
					System.out.println("Exiting the application. Goodbye!");
					running = false; // Exit the loop
					break;

				default:
					System.out.println("Invalid choice. Please try again.");
				}
			}
		} else
			System.out.println("Invalid credentials. Please try again.");
	}

}
