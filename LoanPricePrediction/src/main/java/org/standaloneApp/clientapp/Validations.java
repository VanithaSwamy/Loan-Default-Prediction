package org.standaloneApp.clientapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//to add validations in project
 class Validations {
	
	 /*--------TO VALIDATE USERNAME----------*/
	public boolean isNameValidate(String name) {
		if(name == null || name.isEmpty()) {
			return false;
		}else if(name.length() > 30) {
			return false;
		}else if(name.matches("^[a-zA-Z\\s]+$"))
		{
			return true;
		}
		return false;
	}
	
	/*-------TO VALIDATE USER DATE-------*/
	public boolean isDateValidate(java.util.Date newDate) throws ParseException {
		SimpleDateFormat sDF = new SimpleDateFormat("yyyy-MM-dd");
		Date currDate = new Date();
		String formatDate = sDF.format(currDate);
		Date parseCurrDate = sDF.parse(formatDate);
		
		if(newDate.before(parseCurrDate)) {
			return true;
		}
		return false;
	}
	
	/*---------TO VALIDATE USER PHONE NUMBER*/
	public boolean isPhoneNumbValidate(String newPhoneNumb) {
			//to check if phone number is not equal to 10digit
			if(newPhoneNumb.length() != 10)
			{
				return false;
			}
	
			//to check all digit are number
			for(int i=0; i < newPhoneNumb.length();i++) {
				if(!Character.isDigit(newPhoneNumb.charAt(i))) {
					return false;
				}
			}
			//to check first digit start with 9 or 7
			if(newPhoneNumb.charAt(0) < '7' || newPhoneNumb.charAt(0) > '9') {
				return false;
			}
	return true; //if it doesn't matches above negative conditions ie number is indian and contain 10digit
	}
}
