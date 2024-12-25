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
				Function.userLogin(admin,borrowerService);
				break;

			case 2:
				Function.adminLogin(admin,borrowerService);
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

}
