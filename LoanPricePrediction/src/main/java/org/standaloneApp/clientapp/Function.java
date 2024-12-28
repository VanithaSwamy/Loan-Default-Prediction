package org.standaloneApp.clientapp;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.IntConsumer;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.standaloneApp.model.BorrowerModel;
import org.standaloneApp.model.CreditModel;
import org.standaloneApp.model.IncomeModel;
import org.standaloneApp.model.LoanModel;
import org.standaloneApp.repository.BorrowerRepositoryImpl;
import org.standaloneApp.service.AdminService;
import org.standaloneApp.service.BorrowerService;
import org.standaloneApp.service.BorrowerServiceImpl;


public class Function {
	private static Scanner sc = new Scanner(System.in);
	private static Validations validate = new Validations();
	private static Logger logger = Logger.getLogger(BorrowerRepositoryImpl.class);
	static {
		PropertyConfigurator.configure("C:\\Users\\Admin\\git\\Loan-Default-Prediction\\LoanPricePrediction\\src\\main\\resources\\application.properties");
		logger.setLevel(Level.ALL);
	}
	static void adminLogin(AdminService admin, BorrowerService borrowerService) {
		sc.nextLine();
		System.out.println("---- Admin Login ----");
		System.out.print("Enter admin username: ");
		String adminUsername = sc.nextLine();
		System.out.print("Enter admin password: ");
		String adminPassword = sc.nextLine();

		if (admin.adminLogin(adminUsername, adminPassword)) {
			boolean running = true;
			while (running) {
				logger.info("Admin Login to Application");
				
				System.out.println("\n--- Welcome to Admin Portal ---");
				System.out.println("1: View All Borrowers");
				System.out.println("2: Delete Borrower");
				System.out.println("3: Add Loan Types");
				System.out.println("4: Check Evaluation for Bulk Data");
				System.out.println("5: Exit");
				System.out.println("6: To apply Algorithm:");
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
					addLoanType(admin,borrowerService);
					break;

				case 4:
					// check evaluation for bulk data
					break;

				case 5:
					logger.info("Admin LoginOut from Application");
					
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

	//to apply algo
 	static void userLogin(AdminService admin, BorrowerService borrowerService) {
		sc.nextLine();//
		System.out.println("---- User Login ----");
		System.out.print("Enter username: ");
		String borrName = sc.nextLine();
		System.out.print("Enter Id proof number: ");
		String idno = sc.nextLine();

		if (borrowerService.isBorrowerPresent(borrName, idno)) {
			boolean running = true;

			do {
				logger.info("User Login into Application");
//				System.out.println("1: Add Borrower details \n2: View Borrower Details \n3: Update Borrower Details \n4: Delete Borrower record \n5: Add Data for Loan Evaluation \n6: Exit \nEnter choice : ");
				System.out.println(
						"\n2: View Borrower Details "
						+ "\n3: Update Borrower Details"
						+ " \n4: Delete Borrower record "
						+ "\n5: Add Data for Loan Evaluation "
						+ " \n6: Exit \\n7: To Predict loan \nEnter choice : ");
				int ch = sc.nextInt();

				switch (ch) {
//				case 1:
//					Function.addBorrower(borrowerService);
//					break;

				case 2:
					Function.getDetails(borrowerService,borrName,idno);
					break;
					
				case 3:
					Function.updateBorrower(borrowerService,borrName,idno);
					break;

				case 4:
					Function.deleteBorrower(borrowerService);
					break;

				case 5:
					Function.DataEvaluation(borrowerService,borrName,idno);
					break;

				case 6:
					logger.info("User LoginOut from Application");
					System.out.println("Exiting the program...");
					running = false; // Stop the loop
					break;
				case 7:
					sc.nextLine();
					System.out.println("Do you Want to Apply for Predection? Yes/No");
					String decision = sc.nextLine();
//					if(decision.equals("YES")) {
//						Function.loanPrediction(borrowerService,borrName,idno);
//					}else {
//						System.out.println("No Prediction for current borrower......");
//					}
					 if (decision.equalsIgnoreCase("yes")) {
				            // Process loan prediction
				                } else {
				            System.out.println("Prediction not requested.");
				        }
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
					boolean valName = validate.isNameValidate(borrName);
					boolean valProof = validate.isIdProofValidate(idno);

					System.out.println("Enter Date of birth in yyyy-MM-dd format ");
					String dob = sc.nextLine();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date utilDate = dateFormat.parse(dob);
					Date sqlDate = new Date(utilDate.getTime());
					boolean valDate= validate.isDateValidate(sqlDate);
					
					System.out.println("Enter Contact number ");
					String phno = sc.nextLine();
					boolean valPhno = validate.isPhoneNumbValidate(phno);

					System.out.println("Enter Email ID ");
					String em = sc.nextLine();
					boolean valEm = validate.isEmailAdrsValidate(em);

					if (valName && valPhno && valEm && valProof && valDate) {
						System.out.println(borrowerService
								.isAddNewBorrower(new BorrowerModel(0, borrName, sqlDate, phno, em, idno))
										? "Borrower registered successfully...."
										: " Registeration failed ");
					} else if (valName == false) {
						System.out.println("Enter valid borrower name");
						logger.info("User enter wrong Name :"+valName);
					} else if (valPhno == false) {
						System.out.println("Enter valid phone number");
						logger.info("User enter wrong Phone Number: "+valPhno);
					} else if (valEm == false) {
						System.out.println("Enter valid email Id");
						logger.info("User enter wrong Email Address: "+valEm);
					} else if (valProof ==  false) {
						System.out.println("Enter valid Id Proof ");
						logger.info("User enter wrong ID Proof: "+valProof);
					} else if (valDate == false){
						System.out.println("Enter valid Date  ");
						logger.info("User enter wrong Date:"+valDate);
					}
					else {
						System.out.println("Invalid data entered...");
						logger.info("Invalid data entered...");
					}
				} catch (Exception e) {
					logger.info("Unable to Register New Borrower in Application "+e);
					System.out.println("Error to Register New Borrower");
				}
			} else {
				System.out.println("Thank You For Response");
			}
		}

	}
// 	public static void loanPrediction(BorrowerService borrowerService,String borrName,String idProof) {
// 			try {
// 					boolean loanApproval = borrowerService.loanPredictionAlgo(borrName,idProof);
// 					if(loanApproval) {
// 						System.out.println("Loan Approved");
// 					}else {
// 						System.out.println("Loan Not Approved");
// 					}
// 			}catch(Exception ex) {
// 				System.out.println("Error in loanPrediction method:"+ex);
// 			}
// 	}
 	
 	//Loan Type
  		public static void addLoanType(AdminService admin, BorrowerService borrowerService) {
 			try {
 				boolean run = true;
 					while(run){
 						sc.nextLine();
 						System.out.println("\nEnter below choice:"
 								+ "\n1:Display All Avl Loan Type"
 								+ "\n2:Add New Loan Type"
 								+ "\n3:Update Loan Type Name"
 								+ "\n4:Delete Loan Type"
 								+ "\n5:Exit");
 						int choice = sc.nextInt();
 						switch(choice) {
 						case 1:
 							Optional<Map<Integer,String>> loanTypes = admin.getLoanType();
 							System.out.println("Loan Types:");
 					        loanTypes.ifPresentOrElse(
 					            map -> map.forEach((key, value) -> {
 					                Optional<String> optionalValue = Optional.ofNullable(value);
 					                optionalValue.ifPresent(
 					                    loanName -> System.out.println("LoanType ID: " + key + ", LoanType Name: " + loanName)
 					                );
 					            }),
 					            () -> System.out.println("No loan types available.")
 					        );
 							
 							break;
 						case 2:
 							try {
 								sc.nextLine();
 								System.out.println("Enter New LoanType:");
 								String ltype = sc.nextLine();
 								boolean valName = validate.isNameValidate(ltype);
 								if(valName) {
 									if(admin.isAddNewLoanType(ltype)) 
 										System.out.println("Successfully...Added new Loan");
 									else 
 										System.out.println("Error to add new Loan");
 									
 								}else {
 									System.out.println("Please Enter Correct Loan Name");
 								}
 							}catch(Exception ex) {
 								System.out.println("Error to add Loan Type");
 								logger.error("Error to add Loan Type:"+ex);
 							}
 							break;
 						case 3:
 							try {
 								sc.nextLine();
 								System.out.println("Enter old Loan Name to update");
 								String oldName = sc.nextLine();
 								System.out.println("Enter new Name :");
 								String newName = sc.nextLine();
 								
 								if(admin.updateLoanName(oldName, newName)) {
 									System.out.println("Loan Name Update");
 								}else {
 									System.out.println("Loan Name Not Updated!!");
 								}
 						}catch(Exception ex) {
 							System.out.println("Error to update Loan Type from Function:");
 							logger.error("Error to update Loan Type from Function:"+ex);
 						}
 							break;
 						case 4:
 							try {
 								sc.nextLine();
 								System.out.println("Enter Loan Name to delete:");
 								String currName = sc.nextLine();
 								if(admin.deleteLoanName(currName)) {
 									System.out.println("Name Deleted Successfully...");
 								}else {
 									System.out.println("Name not Deleted...Sorry");
 								}
 							}catch(Exception ex) {
 								System.out.println("Error to Delete Loan Type from Function:"+ex);
 								logger.error("Error to Delete Loan Type from Function:"+ex);
 							}
 							break;
 						case 5:System.out.println("Out of Loan Type");
 							run = false;
 							break;
 						default:System.out.println("Wrong Choice");
 							break;
 						}	
 					}
 			}catch(Exception ex) {
 				System.out.println("Exception in AddLoanType: "+ex);
 				logger.error("Exception in AddLoanType: "+ex);
 			}
 		}

	public static void addBorrower(BorrowerService borrowerService) {
		try {
			sc.nextLine();
			System.out.println("Enter Borrower name ");
			String bnm = sc.nextLine();
			boolean valName = validate.isNameValidate(bnm);

			System.out.println("Enter Date of birth in yyyy-MM-dd format ");
			String dob = sc.nextLine();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utilDate = dateFormat.parse(dob);
			Date sqlDate = new Date(utilDate.getTime());
			boolean valDate= validate.isDateValidate(sqlDate);

			System.out.println("Enter Contact number ");
			String phno = sc.nextLine();
			boolean valPhno = validate.isPhoneNumbValidate(phno);

			System.out.println("Enter Email ID ");
			String em = sc.nextLine();
			boolean valEm = validate.isEmailAdrsValidate(em);

			System.out.println("Enter Id proof number ");
			String idno = sc.nextLine();
			boolean valProof = validate.isIdProofValidate(idno);

			if (valName && valDate && valPhno && valEm && valProof) {
				System.out.println(borrowerService.isAddNewBorrower(new BorrowerModel(0, bnm, sqlDate, phno, em, idno))
						? "Borrower registered successfully...."
						: " Registeration failed ");
			} else if (valName == false) {
				System.out.println("Enter valid borrower name");
			} else if (valPhno == false) {
				System.out.println("Enter valid phone number");
			} else if (valEm == false) {
				System.out.println("Enter valid email Id");
			} else if (valProof == false) {
				System.out.println("Enter valid Id Proof ");
			} else if (valDate == false){
				System.out.println("Enter valid Date  ");
			}else {
				System.out.println("Invalid data entered...");
			}

		} catch (Exception e) {
			System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
		}
	}

	public static void displayBorrower(BorrowerService borrowerService) {
		Optional<List<BorrowerModel>> borrowerList = borrowerService.getAllBorrowers();
		borrowerList.ifPresent(list -> list.forEach(borr -> System.out.println(borr.getBid() + "\t" + borr.getName()
				+ "\t" + borr.getDob() + "\t" + borr.getPhno() + "\t" + borr.getEmail() + "\t" + borr.getId_proof())));
	}

	public static void updateBorrower(BorrowerService borrowerService,String borrName,String idno) {
//		sc.nextLine();
//		System.out.println("Enter borrower name :");
//		String currBName = sc.nextLine();
//		System.out.println("Enter borrower id_proof:");
//		String idProof = sc.nextLine();
		String currBName = borrName;
		String idProof = idno;
		boolean borrPresent = borrowerService.isBorrowerPresent(currBName, idProof);

		if (borrPresent) {
			do {
				System.out.println("\nPlease enter below options to update the details" + "\n1:To Update Name"
						+ "\n2:To Update Date of Birth" + "\n3:To Update Contact Number" + "\n4:To update Email address"
						+ "\n5: Exit");

				int updateChoice = sc.nextInt();
				switch (updateChoice) {
				case 1:
					// to update UserName
					sc.nextLine();
					System.out.println("Enter New Name:");
					String newName = sc.nextLine();
					if(validate.isNameValidate(newName)) {
						boolean newN = borrowerService.isUpdateNewName(currBName, idProof, newName);
						if (newN)
							System.out.println("New Name Update Successfully...");
						else
							System.out.println("New Name Not Updated...from DB");
					}else
					{
						System.out.println("New Name Not Updated...from Client Side");
					}

					break;
				case 2:
					// to update UserBirth date
					try {
						sc.nextLine();
						System.out.println("Enter Date of birth in yyyy-MM-dd format ");
						String newD = sc.nextLine();
						SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
						java.util.Date utilDate1 = dateFormat1.parse(newD);
						boolean isValidDate = validate.isDateValidate(utilDate1); //to add date validation
						if(isValidDate)
						{
							Date sqlDate1 = new Date(utilDate1.getTime());//to convert into date from java to sql
							boolean newBDate = borrowerService.isUpdateNewBDate(currBName, idProof, sqlDate1);
							if (newBDate)
								System.out.println("New Birth Date Update Successfully...");
							else
								System.out.println("New Birth Date Not Updated...DB");

						}else {
							System.out.println("New Birth Date Not Updated...ClientApp");
						}
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
					boolean validPNumb = validate.isPhoneNumbValidate(newBPhoneNumb1);
					if(validPNumb) {
						boolean b2 = borrowerService.isUpdatePhoneNumb(currBName, idProof, newBPhoneNumb1);
						if (b2)
							System.out.println("Phone Number  Update Successfully...");
						else
							System.out.println("Phone Number Not Updated...");
					}else {
						System.out.println("Phone Number Not Updated...ClientApp");
					}	

					break;
				case 4:
					// to update User Email address
					sc.nextLine();
					System.out.println("Enter new Email address");
					String newEmailAdrs1 = sc.nextLine();
					boolean newEmailAdrsVal = validate.isEmailAdrsValidate(newEmailAdrs1);
					if(newEmailAdrsVal) {
						boolean b11 = borrowerService.isUpdateEmailAdrs(currBName, idProof, newEmailAdrs1);
						if (b11)
							System.out.println("Email Address Updated Successfully....");
						else
							System.out.println("Email Address Not Updated");
					}else {
						System.out.println("InValid email..please enter valid email adrs");
					}
					break;

				case 5:
					System.out.println("Exiting update function...");
					return; // Exit only `updateBorrower` method
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			} while (true);
		} else {
			System.out.println(currBName + " is Not Present in database table...so no update");
		}
	}

	public static void deleteBorrower(BorrowerService borrowerService) {
		sc.nextLine();
		System.out.println("Enter Id proof number to delete record");
		String idno = sc.nextLine();
		System.out.println(borrowerService.deleteBorrowerById(idno) ? "Borrower deleted successfully...."
				: "Failed to delete borrower ");
	}

	public static void DataEvaluation(BorrowerService borrowerService,String borrName,String idProof) {
		// Add Data for Loan Evaluation
		sc.nextLine();
		
			int borrid=borrowerService.getBorrowerId(idProof);
			
			System.out.println("Enter income : ");
			double income=sc.nextDouble();
			sc.nextLine();
			
			System.out.println("Enter income source : ");
			String income_source=sc.nextLine();
			
		    IncomeModel incomeModel = new IncomeModel(0, income, income_source);
		    if (!borrowerService.addIncome(incomeModel, borrid)) {
		        System.out.println("Unable to add income.");
		        return;
		    }

		    System.out.println("Enter credit score: ");
		    int creditScore = sc.nextInt();

		    CreditModel creditModel = new CreditModel(0, creditScore);
		    if (!borrowerService.addCredit(creditModel, borrid)) {
		        System.out.println("Unable to add credit score.");
		        return;
		    }

		    sc.nextLine();
		    System.out.println("List of available loan types: ");
		    borrowerService.getLoanType().ifPresentOrElse(
		        loanList -> loanList.forEach(System.out::println),
		        () -> System.out.println("No loan types available.")
		    );

		    System.out.println("Enter loan type: ");
		    String loanType = sc.nextLine();
		    int loanTypeId = borrowerService.getLoanTypeId(loanType);

		    if (loanTypeId == -1) {
		        System.out.println("Invalid loan type.");
		        return;
		    }

		    System.out.println("Enter required loan amount: ");
		    double loanAmount = sc.nextDouble();

		    LoanModel loanModel = new LoanModel(0, loanType, loanAmount);
		    if (borrowerService.addLoanAmt(loanModel, borrid, loanTypeId)) {
		        System.out.println("Loan amount added successfully.");
		    } else {
		        System.out.println("Failed to add loan amount.");
		    }
		    
		    System.out.println("Enter Ok for prediction : ");
		    String msg=sc.nextLine();
		    if(msg.equalsIgnoreCase("Ok"))
		    {
		    	
		    }
		    else {
		    	System.out.println("Thanks for your response");
		    }
	}
	
	public static void getDetails(BorrowerService borrowerService,String borrName,String idno) {
//		sc.nextLine();
//		System.out.println("Enter borrower name :");
//		String currBName1 = sc.nextLine();
//		System.out.println("Enter borrower id_proof:");
//		String idProof1 = sc.nextLine();
		
		String currBName1 = borrName;
		String idProof1 = idno;
		
		Optional<BorrowerModel> borrModel = borrowerService.getBorrower(currBName1, idProof1);
	    borrModel.ifPresentOrElse(
	    		borr -> System.out.println("Borrower Found:\n" +borr.getBid() + "\t" + borr.getName() + "\t" + borr.getDob()
				+ "\t" + borr.getPhno() + "\t" + borr.getEmail() + "\t" + borr.getId_proof()),
	        () -> System.out.println("Borrower not found")
	    );
	}
}