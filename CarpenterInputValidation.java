/* 	Author: Zachary Carpenter
	Date Date: 12/06/2021
 	Purpose: This program contains some utility classes that validates the 
 	user input. It includes a method to validate the course number, user input
 	from strings or doubles (overloaded method). And finally, a method to 
 	validate the location input - throwing a custom exception to help the user.
*/

public class CarpenterInputValidation {

	/**
	 * isValidCourseNum checks if the course name is formatted properly
	 * @param cn is the course number string
	 * @return isValid boolean whether the string is formatted properly
	 * LLL-XXX (ex. CSC-151)
	 */
	public boolean isValidCourseNum(String cn) {
		// initialize our valid boolean
		boolean isValid = true;
		
		// check if the employee number is the correct length first
		if (cn.length() != 7) {
			isValid = false;
		}
		else {
			/* check if the first 3 digits are letters
			 * the 4th digit is a hyphen
			 * and the last 3 digits are a number
			 */
			if ( (!Character.isLetter(cn.charAt(0)))
				|| (!Character.isLetter(cn.charAt(1)))
				|| (!Character.isLetter(cn.charAt(2)))
				|| ((cn.charAt(3) != '-') 
				|| (!Character.isDigit(cn.charAt(4)))
				|| (!Character.isDigit(cn.charAt(5)))
				|| (!Character.isDigit(cn.charAt(6)))) ) {
					
				isValid = false;
			}	
		}
		return isValid;
	}
	
	/**
	 * isValidInput checks if the string is a made up of letters, not null, and
	 * not a blank string OR if the string can be parsed to a double 
	 * between 0-100
	 * @param s is the string from the user input
	 * @return isValidStr a boolean if the user input string is valid
	 */
	public boolean isValidInput(String s) {
		boolean isValidStr = false;
		
		// uses a regex to check if the string entered is between A-Z or a-z
		if ((s.matches("[A-Za-z]*") && s != null && !s.equals(""))
				|| (Double.parseDouble(s) <= 100 
				&& Double.parseDouble(s) >= 0)) {
			isValidStr = true;
		}
		
	    return isValidStr;
	}
	
	/**
	 * isValidInput overloads and checks if the average enter is valid
	 * @param avg is the user input average for the student
	 * @return isValidAvg a boolean if the avg value is valid
	 */
	public boolean isValidInput(double avg) {
		boolean isValidAvg = false;
		
		if (avg >= 0 && avg <= 100) {
			isValidAvg = true;
		}
		
		return isValidAvg; 
	}
	
	/**
	 * isValidLocation checks if the location entered equals either MAIN CAMPUS
	 * or ONLINE throws custom exception if the value is incorrect
	 * @param l is the location string passed from user input
	 * @return validLocation is a boolean of whether the location is valid
	 */
	public boolean isValidLocation(String l) throws CarpenterInvalidInputEx {
		boolean validLocation = false;
		
		if (l.equals("MAIN CAMPUS") || l.equals("ONLINE")) {
			validLocation = true;
		}
		else {
			throw new CarpenterInvalidInputEx(l);
		}
		
		return validLocation;
	}
}
