/* 	Author: Zachary Carpenter
	Date Date: 12/06/2021
 	Purpose: This program extends the Exception class and is used to call a
 	custom exception when the input is invalid.
*/

public class CarpenterInvalidInputEx extends Exception {

	public CarpenterInvalidInputEx(String e) {
		super("Input Error: '" + e + "' is not a valid location name.\n"
				+ "Location should be either 'Main Campus' or 'Online'");
	}
}
