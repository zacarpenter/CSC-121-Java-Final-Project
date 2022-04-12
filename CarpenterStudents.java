/* 	Author: Zachary Carpenter
	Date Date: 12/06/2021
 	Purpose: This program extends the Classroom class and holds info for the
 	students, averages, and letter grades. This program allows users to get 
 	the values in those ArrayLists. Finally, it overrides the toString method
 	calling the super class constructor and printing custom output to the
 	console.
*/

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class CarpenterStudents extends CarpenterClassroom {

	// private fields
	final int MAX_STUDENTS = 25; // maximum seats in course
	private ArrayList<String> students;
	private ArrayList<Double> averages;
	private ArrayList<String> letterGrades;
	
	/**
	 * no-arg constructor - calls super class constructor and creates new
	 * ArrayLists to hold my info
	 */
	public CarpenterStudents() {
		super();
		students = new ArrayList<String>(MAX_STUDENTS);
		averages = new ArrayList<Double>(MAX_STUDENTS);
		letterGrades = new ArrayList<String>(MAX_STUDENTS);
	}
	
	/**
	 * getStudents provides the list of students added to the ArrayList
	 * @return students ArrayList
	 */
	public ArrayList<String> getStudents() {
		return students;
	}
	
	/**
	 * getGrades provides the list of grades added to the ArrayList
	 * @return averages ArrayList
	 */
	public ArrayList<Double> getAverages() {
		return averages;
	}
	
	/**
	 * letterGrades evaluates the ArrayList of averages and adds the
	 * corresponding letter grade to the letterGrades ArrayList
	 * uses the BigDecimal library to round the averages index to the nearest
	 * tenth place
	 * @return letterGrades ArrayList
	 */
	public ArrayList<String> letterGrades() {
		
		for (int i = 0; i < averages.size(); i++) {
			BigDecimal bd = 
				new BigDecimal(averages.get(i)).setScale(1, 
						RoundingMode.HALF_UP);

			if (Double.valueOf(String.valueOf(bd)) >= 89.5) {
				letterGrades.add(i, "A");
			}
			else if (Double.valueOf(String.valueOf(bd)) >= 79.5) {
				letterGrades.add(i, "B");
			}
			else if (Double.valueOf(String.valueOf(bd)) >= 69.5) {
				letterGrades.add(i, "C");
			}
			else if (Double.valueOf(String.valueOf(bd)) >= 59.5) {
				letterGrades.add(i, "D");
			}
			else {
				letterGrades.add(i, "F");
			}
		}
		
		return letterGrades;
	}
	
	/**
	 * toString overrides the default method and calls the super class toString
	 * this method formats the output that will be displayed
	 * uses the BigDecimal library to round the averages index to the nearest
	 * hundredth place to display
	 * @return str which is the formatted string
	 */
	public String toString() {
		String str = super.toString();
		
		str += String.format("\n\n %-12s%-12s%-12s\n"
				+ "-------------------------------------", 
				"Student", "Average", "Letter Grade");
		
		/*
		 *  loop through the students, averages, and letterGrades ArrayList 
		 *  and print the info
		 */
		for (int i = 0; i < MAX_STUDENTS; i++) {
			BigDecimal bd = new BigDecimal(averages.get(i)).setScale(2, 
					RoundingMode.HALF_UP);
			str += String.format("\n%d. %s%-10s %-12.2f %s\n"
					+ "-------------------------------------", 
					(i+1), 
					students.get(i).substring(0,1).toUpperCase(),
					students.get(i).substring(1),
					Double.valueOf(String.valueOf(bd)),
					// get the value of - the value of the string
					letterGrades().get(i)); // call letterGrades()
		}

		return str;
	}
}
