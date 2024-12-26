package org.standaloneApp.clientapp;

import java.util.*;
import org.standaloneApp.service.*;

public class LoanDeafultPredictionApp {
	private static AdminService admin = new AdminServiceImpl();
	private static BorrowerService borrowerService = new BorrowerServiceImpl();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		boolean running = true;

		while (running) {
			System.out.println("Welcome to the Portal");
			System.out.println("1: User Login");
			System.out.println("2: User Register");
			System.out.println("3: Admin");
			System.out.println("4: Exit");
			System.out.print("Enter your choice: ");

			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				Function.userLogin(admin,borrowerService);
				break;
			
			case 2:
				Function.addBorrower(borrowerService);
				break;
				
			case 3:
				Function.adminLogin(admin,borrowerService);
				break;

			case 4:
				System.out.println("Exiting the application. Goodbye!");
				running = false; // Exit the loop
				break;

			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
		sc.close();
	}

}
