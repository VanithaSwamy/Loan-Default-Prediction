package org.standaloneApp.clientapp;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.standaloneApp.model.BorrowerModel;
import org.standaloneApp.service.AdminService;
import org.standaloneApp.service.BorrowerService;
import org.standaloneApp.service.BorrowerServiceImpl;

public class Function {
	private static Scanner sc = new Scanner(System.in);
	
	static void adminLogin(AdminService admin,BorrowerService borrowerService ) {
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
					displayBorrower(borrowerService);
					break;

				case 2:
					deleteBorrower(borrowerService);
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
	
	static void userLogin(AdminService admin,BorrowerService borrowerService) {

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
	public static void addBorrower(BorrowerService borrowerService ) {
		try {
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

			System.out.println(
					borrowerService.isAddNewBorrower(new BorrowerModel(0, bnm, sqlDate, phno, em, idno))
							? "Borrower registered successfully...."
							: " Registeration failed ");

		} catch (Exception e) {
			System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
		}
	}
	
	public static void displayBorrower(BorrowerService borrowerService ) {
		Optional<List<BorrowerModel>> borrowerList = borrowerService.getAllBorrowers();
		borrowerList.ifPresent(
				list -> list.forEach(
						borr -> System.out.println(borr.getBid() + "\t" + borr.getName() + "\t" + borr.getDob()
								+ "\t" + borr.getPhno() + "\t" + borr.getEmail() + "\t" + borr.getId_proof())));
	}
	
	public static void updateBorrower(BorrowerService borrowerService ) {

		System.out.println("Enter borrower name :");
		String currBName = sc.nextLine();
		System.out.println("Enter borrower id_proof:");
		String idProof = sc.nextLine();
		boolean borrPresent = borrowerService.isBorrowerPresent(currBName, idProof);
		
		if (borrPresent) {
			do {
			System.out.println("\nPlease enter below options to update the details" + "\n1:To Update Name"
					+ "\n2:To Update Date of Birth" + "\n3:To Update Contact Number"
					+ "\n4:To update Email address"+"\n5: Exit");

			int updateChoice = sc.nextInt();
			switch (updateChoice) {
			case 1:
				// to update UserName
				sc.nextLine();
				System.out.println("Enter New Name:");
				String newName = sc.nextLine();
				boolean newN = borrowerService.isUpdateNewName(currBName, idProof, newName);
				if (newN)
					System.out.println("New Name Update Successfully...");
				else
					System.out.println("New Name Not Updated...");

				break;
			case 2:
				// to update UserBirth date
				try {
					sc.nextLine();
					System.out.println("Enter Date of birth in yyyy-MM-dd format ");
					String newD = sc.nextLine();
					SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date utilDate1 = dateFormat1.parse(newD);
					Date sqlDate1 = new Date(utilDate1.getTime());
					boolean newBDate = borrowerService.isUpdateNewBDate(currBName, idProof, sqlDate1);
					if (newBDate)
						System.out.println("New Birth Date Update Successfully...");
					else
						System.out.println("New Birth Date Not Updated...");

				} catch (Exception ex) {
					System.out.println("Error message: " + ex);
					System.out.println("Unable to update user birth date ");
				}
				break;
			case 3:
				// to update User Contact
				sc.nextLine();
				System.out.println("Enter new Phone:");
				String newBPhoneNumb1 = sc.nextLine();

				boolean b2 = borrowerService.isUpdatePhoneNumb(currBName, idProof, newBPhoneNumb1);
				if (b2)
					System.out.println("Phone Number  Update Successfully...");
				else
					System.out.println("Phone Number Not Updated...");

				break;
			case 4:
				// to update User Email address
				sc.nextLine();
				System.out.println("Enter new Email address");
				String newEmailAdrs1 = sc.nextLine();
				boolean b11 = borrowerService.isUpdateEmailAdrs(currBName, idProof, newEmailAdrs1);
				if (b11)
					System.out.println("Email Address Updated Successfully....");
				else
					System.out.println("Email Address Not Updated");

				break;
				
			case 5:
				 System.out.println("Exiting update function...");
		         return; // Exit only `updateBorrower` method
			default:
				System.out.println("Invalid choice. Please try again.");
			}
			}while(true);
		} 
		else {
			System.out.println(currBName + " is Not Present in database table...so no update");
		}
	}
	
	public static void deleteBorrower(BorrowerService borrowerService ) {
//		sc.nextLine();
		System.out.println("Enter Id proof number to delete record");
		String idno = sc.nextLine();
		System.out.println(borrowerService.deleteBorrowerById(idno) ? "Borrower deleted successfully...."
				: "Failed to delete borrower ");
	}

	public static void DataEvaluation(BorrowerService borrowerService ) {
		// Add Data for Loan Evaluation
//		sc.nextLine();
		System.out.println("Enter borrower name :");
		String borrName = sc.nextLine();
		System.out.println("Enter borrower id_proof:");
		String idProof = sc.nextLine();

		boolean borrPresent = borrowerService.isBorrowerPresent(borrName, idProof);
		if (borrPresent) {
			
				//Accept income,income_source,credit_score,loan_type,loan_amt
			
		} else {
			System.out.println("Oops Details Not Found !!!!");
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
							borrowerService.isAddNewBorrower(new BorrowerModel(0, borrName, sqlDate, phno, em, idProof))
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
}