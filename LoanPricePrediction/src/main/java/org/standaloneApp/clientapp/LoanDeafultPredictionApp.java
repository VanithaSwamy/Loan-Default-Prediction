package org.standaloneApp.clientapp;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.*;

import org.standaloneApp.model.BorrowerModel;
import org.standaloneApp.service.BorrowerServiceImpl;
import org.standaloneApp.service.BorrowerService;


public class LoanDeafultPredictionApp {
	
	public static int option;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		BorrowerService borrowerService = new BorrowerServiceImpl();

		do {
			System.out.println(
					"1. Add Borrower details \n2. Display Borrower details \n3. Update Borrower Details "
					+ "\n4. Delete Borrower record \n5. Exit \nEnter choice : ");
			int ch = sc.nextInt();

			switch (ch) {
			/*------TO add single borrower--------*/
			case 1:
				Validations validate1 = new Validations();
				
				try {
					sc.nextLine();

					System.out.println("Enter Borrower name ");
					String bnm = sc.nextLine();
					
					//to add validation in username
					boolean valName = validate1.isNameValidate(bnm); //to check name validate
					
					System.out.println("Enter Date of birth in yyyy-MM-dd format ");
					String dob = sc.nextLine();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date utilDate = dateFormat.parse(dob);
					boolean valDate = validate1.isDateValidate(utilDate);//to check date validate
					Date sqlDate = new Date(utilDate.getTime());

					System.out.println("Enter Contact number ");
					String phno = sc.nextLine();
					boolean valPhone = validate1.isPhoneNumbValidate(phno);//to check phone validate
					
					System.out.println("Enter Email ID ");
					String em = sc.nextLine();
					boolean valEmail = validate1.isEmailAdrsValidate(em);

					System.out.println("Enter Id proof number ");
					String idno = sc.nextLine();
					boolean valIdProof = validate1.isIdProofValidate(idno);

					//if all validation are correct
					if(valName && valDate && valPhone && valEmail && valIdProof) {
						System.out.println(
								borrowerService.isAddNewBorrower(new BorrowerModel(0, bnm, sqlDate, phno, em, idno))
										? "Borrower registered successfully...."
										: " Registeration failed ");

					}else {
						System.out.println("Error from client to add New Borrower");
						
					}
				} catch (Exception e) {
					System.out.println("Invalid data please enter valid data And Error:"+e);
				}
				break;
				
				/*---------------------------to display only single matching borrower record----------------*/
			case 2:
				sc.nextLine();
				System.out.println("Enter borrower name :");
				String currBName1 = sc.nextLine();
				System.out.println("Enter borrower id_proof:");
				String idProof1 = sc.nextLine();
				
				Optional<BorrowerModel> borrModel = borrowerService.getBorrower(currBName1, idProof1);
			    borrModel.ifPresentOrElse(
			    		borr -> System.out.println("Borrower Found:\n" +borr.getBid() + "\t" + borr.getName() + "\t" + borr.getDob()
						+ "\t" + borr.getPhno() + "\t" + borr.getEmail() + "\t" + borr.getId_proof()),
			        () -> System.out.println("Borrower not found")
			    );
				break;
//				Optional<List<BorrowerModel>> borrowerList = borrowerService.getAllBorrowers();
//				borrowerList.ifPresentOrElse(
//						list -> list.forEach(
//								borr -> System.out.println(borr.getBid() + "\t" + borr.getName() + "\t" + borr.getDob()
//										+ "\t" + borr.getPhno() + "\t" + borr.getEmail() + "\t" + borr.getId_proof())),
//						() -> System.out.println("There is no data present in table"));


			case 4:
				sc.nextLine();
				System.out.println("Enter Id proof number to delete record");
				String idno = sc.nextLine();
				System.out.println(borrowerService.deleteBorrowerById(idno) ? "Borrower deleted successfully...."
						: "Failed to delete borrower ");
				break;
/*--------------------TO UPDATE USER DETAILS-----------------------------*/
			case 3:
				
				// to update borrower details
				sc.nextLine();
				System.out.println("Enter borrower name :");
				String currBName = sc.nextLine();
				System.out.println("Enter borrower id_proof:");
				String idProof = sc.nextLine();

				boolean borrPresent = borrowerService.isBorrowerPresent(currBName, idProof);
				if (borrPresent) {
					System.out.print(currBName + " is present in database table");
					
					do {
						Validations validate = new Validations(); //to create object of Validations
						
						System.out.println("\nPlease enter below options to update the details "
								+ "\n1:To Update Name"
								+ "\n2:To Update Date of Birth" 
								+ "\n3:To Update Contact Number"
								+ "\n4:To update Email address");
//						sc.nextLine();
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
//								System.out.println("New date:"+utilDate1);
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
						default:
							System.out.println("Worng choice..Please enter correct choice!");
							break;
						}
						System.out.println("Do you want to continue with update..1 --> for yes ?");
						 option = sc.nextInt();
						
					}while(option == 1);
				} else {
					System.out.println(currBName + " is Not Present in database table...so no update");
				}
				break;
				
	/*---------TO EXIT form MAIN LoadDefaultApp-----------*/	
			case 5:
				System.exit(0);

			default:
				System.out.println("Invalid choice ....");
				break;
			}
		} while (true);
	}

}
