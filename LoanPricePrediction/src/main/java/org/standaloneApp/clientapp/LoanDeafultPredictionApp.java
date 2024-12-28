package org.standaloneApp.clientapp;

import java.util.*;
import org.apache.log4j.*;
import org.standaloneApp.service.*;
public class LoanDeafultPredictionApp {
	static Logger logger = Logger.getLogger(LoanDeafultPredictionApp.class);
	static {
		PropertyConfigurator.configure("C:\\Users\\Admin\\git\\Loan-Default-Prediction\\LoanPricePrediction\\src\\main\\resources\\application.properties");
	}
	
	private static AdminService admin = new AdminServiceImpl();
	private static BorrowerService borrowerService = new BorrowerServiceImpl();

	public static void main(String[] args) {
		logger.setLevel(Level.DEBUG);
		logger.debug("Main method started and BorrowerSErvice and AdminService Objects Created");
		try {
			Scanner sc = new Scanner(System.in);
			boolean running = true;

			while (running) {
				logger.info("Application running...");
				
				System.out.println("Welcome to the Portal");
				System.out.println("1: User Login");
				System.out.println("2: User Register");
				System.out.println("3: Admin");
				System.out.println("4: Exit");
				System.out.print("Enter your choice: ");

				int choice = sc.nextInt();

				switch (choice) {
				case 1:
					logger.info("Case1: User Login");
					Function.userLogin(admin,borrowerService);
					break;
				
				case 2:
					logger.info("Case2: Borrower Login");
					Function.addBorrower(borrowerService);
					break;
					
				case 3:
					logger.info("Case3 Admin Login");
					Function.adminLogin(admin,borrowerService);
					break;

				case 4:
					logger.debug("Project Closed Successfully...\n");
					System.out.println("Exiting the application. Goodbye!");
					running = false; // Exit the loop
					break;

				default:
					System.out.println("Invalid choice. Please try again.");
					logger.info("Wrong input for LoadDeafaultPredictionApplication...");	
				}
			}
			sc.close();
		}catch(Exception ex) {
			System.out.println("Exception in main:"+ex+"\n Please enter valid input");
			logger.info("Wrong input...");
		}
	}

}
