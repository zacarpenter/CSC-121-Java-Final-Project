/* 	Author: Zachary Carpenter
	Date Date: 12/06/2021
 	Purpose: This program has a constructor that initializes default values.
 	It also sets and gets the values for our fields. Finally, it overrides the
 	toString method with custom output.
*/
public class CarpenterClassroom {
	
	// private fields
	private String courseName;
	private String location;
	private String instructor;
	
	/**
	 * no-arg constructor
	 * set default values as empty strings
	 */
	public CarpenterClassroom() {
		courseName = "";
		location = "";
		instructor = "";
	}
	
	/**
	 * setCourseName sets the value for the course's name field
	 * @param cn is the name of the college course
	 */
	public void setCourseName(String cn) {
		courseName = cn;
	}
	
	/**
	 * setLocation sets the value of the course's location field
	 * @param l is the value of the location of the course
	 */
	public void setLocation(String l) {
		location = l;
	}
	
	/**
	 * setInstructor sets the value of the instructor field
	 * @param i is the value of the course's instructor name
	 */
	public void setInstructor(String i) {
		instructor = i;
	}
	
	/**
	 * getCourseName provides the value of the college course
	 * @return the name of the college course formatted in uppercase letters
	 */
	public String getCourseName() {
		return courseName.toUpperCase();
	}
	
	/**
	 * getLocation provides the value of the course's location
	 * @return the location of the course formatted with the first letter 
	 * capitalized
	 */
	public String getLocation() {
		return location.substring(0, 1).toUpperCase() + location.substring(1);
	}
	
	/**
	 * getInstructor provides the value of the instructor field
	 * @return the instructor's name
	 */
	public String getInstructor() {
		return instructor;
	}
	
	/**
	 * toString overrides the default method and formats the output that 
	 * will be displayed
	 * @return str which is the formatted string
	 */
	public String toString() {
		// call get methods to add the values to the string
		String str = "\nCourse Information\n"
				+ "------------------------\n";
		str += "Course Name: " + getCourseName() + "\nCourse Location: "
				+ getLocation() + "\nCourse Instructor: " + getInstructor();
		
		return str;
	}

}
