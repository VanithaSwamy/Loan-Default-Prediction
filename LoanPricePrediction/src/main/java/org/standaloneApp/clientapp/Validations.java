package org.standaloneApp.clientapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

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
	 
	/*-----------TO VALIDATE USER EMAIL ADDRESS----------*/
	public boolean isEmailAdrsValidate(String newEmail) {
		String str = newEmail;
        int flag = 1; // Initialize to 1, assume valid until proven otherwise

        // Check domain validity
        if (str.endsWith("@gmail.com") || str.endsWith("@outlook.com")) {
            int index = 0;
            int count = 0; // Count of '@' characters

            // Count '@' and find the last index of '@'
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '@') {
                    count++;
                    index = i;
                }
            }
            // If there is exactly one '@' character
            if (count == 1) {
                // Check local part (before '@') for validity
                for (int i = 0; i < index; i++) {
                    char ch = str.charAt(i);
                    if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9'))) {
                        flag = 0; // Invalid if any non-alphanumeric character is found
                        break;
                    }
                }
            } else {
                flag = 0; // Invalid if there isn't exactly one '@'
            }
        } else if(str ==null || str.isEmpty())
        {
            flag = 0;
        }else {
        	flag = 0; 
        }
        // Final output based on flag
        if (flag == 1)
        {
          return true;
        }
        else
        {
        	return false;
        }
    }
	/*--------------To Validate ID PROOF-----------*/
	public boolean isIdProofValidate(String idno) {
		//Pan-card contains 10digit[5char+4num+1char]
		String panCard = idno;
		String panPattern = "[A-Z]{5}[0-9]{4}[A-Z]{1}$";//regex expression for pancard
		return Pattern.matches(panPattern, panCard);
	}
}