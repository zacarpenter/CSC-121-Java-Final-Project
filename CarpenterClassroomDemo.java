/* 	Author: Zachary Carpenter
	Date Date: 12/06/2021
 	Purpose: This program contains my main and a separate buildCourse static
 	method. Main is used to start the program and if the user answers yes, then
 	it calls the buildCourse method. BuildCourse asks the user for input for
 	course number, location, instructor, students, and averages. Finally, it 
 	prints the results to the console.
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class CarpenterClassroomDemo {
	
	public static void main(String[] args) throws CarpenterInvalidInputEx {
		
		// start the program, ask the user if they want to create a course
		int newCourse = JOptionPane.showConfirmDialog(null,
		    "Would you like to create a new course?",
		    "Build a New Course",
		    JOptionPane.YES_NO_OPTION);

		// if yes - call buildCourse
		if (newCourse == JOptionPane.YES_OPTION) {
			buildCourse();
		} // if no - display message and exit program
		else {
			JOptionPane.showMessageDialog(null, "No new course created. If"
					+ " you wish to create a new course, please restart the"
					+ " course builder.", "Exit New Course Builder", 
					JOptionPane.PLAIN_MESSAGE);
		    System.exit(0);
		}
	} // end main
	
	/**
	 * buildCourse creates a new course from user input
	 * it gathers the course name, location, instructor, students (tries to
	 * import from file), averages (also tries to import from file)
	 */
	public static void buildCourse() {
		
		// create a ClassroomValidation object to call methods for valid input
		CarpenterInputValidation input = new CarpenterInputValidation();
		
		// create a CarpenterStudents object to begin building the course
		CarpenterStudents course = new CarpenterStudents();
		
		// Print the user instructions to the console
		System.out.println("Welcome to the course builder!\n"
				+ "Follow the prompts for each step below to build the course."
				+ "\n\n"
				+ String.format("Courses have %d seats available.\n", 
						course.MAX_STUDENTS)
				+ "Step 1. Add a course name.\n"
				+ "Step 2. Add a course location.\n"
				+ "Step 3. Select a course instructor.\n"
				+ "Step 4. Enter student names.\n"
				+ "Step 5. Enter student averages.\n");
		
		// get course name
		String c = (String) JOptionPane.showInputDialog(null,
				"Enter the course number:", 
				"Step 1. Enter Course Number", JOptionPane.QUESTION_MESSAGE, 
				null, null, "Format is LLL-XXX (ex. CSC-151)");

		// check if the cancel button was clicked
		if (c == null) {
			JOptionPane.showMessageDialog(null, "Exiting course builder.");
			System.exit(0);
		}
		
		// enter loop if the course is invalid, ask the user to input again
		while(!input.isValidCourseNum(c)) {
			c = (String) JOptionPane.showInputDialog(null,
				"Please enter a valid course number - formatted LLL-XXX:",
				"Step 1. Enter Course Number", JOptionPane.QUESTION_MESSAGE,
				null, null, "ex. CSC-151");
			
			// check if the cancel button was clicked
			if (c == null) {
				JOptionPane.showMessageDialog(null, "Exiting course builder.");
				System.exit(0);
			}
		}
		
		// set CourseName to the validated value
		course.setCourseName(c);
		
		// always enter the loop to try and get a valid location from the user
		while(true) {
			/**
			 * try to ask the user for a valid location name - either
			 * Main Campus or Online (not case-sensitive)
			 * @exception CarpenterInvalidInput e prints the error message to
			 * the console
			 */
			try {
				// get course location
				String l = (String) JOptionPane.showInputDialog(null, 
						String.format("Enter location for %s:", c.toUpperCase()),
						"Step 2. Enter Course Location", 
						JOptionPane.QUESTION_MESSAGE, null, null, 
						"Main Campus or Online");
		
				// check if the cancel button was clicked
				if (l == null) {
					JOptionPane.showMessageDialog(null, 
							"Exiting course builder.");
					System.exit(0);
				}
		
				// enter loop if location does not equal MAIN CAMPUS or ONLINE
				while(!input.isValidLocation(l.toUpperCase())) {
					l = (String) JOptionPane.showInputDialog(null,
							String.format("Please enter the location"
							+ "for %s as 'Main Campus' or 'Online':",
							c.toUpperCase()), 
							"Step 2. Enter Course Location", 
							JOptionPane.QUESTION_MESSAGE, null, null, 
							"Main Campus or Online");
				}
				// set Location to the validated value
				course.setLocation(l);
				// exit loop when valid input is acquired
				break;	
			} catch(CarpenterInvalidInputEx e) {
				System.out.println(e.getMessage());
			}
		}
		
		// get course instructor
		Object[] instr = {"Dr. Lorrie Tomek", "Kevin Kulp",
				"Karen Schnell"};
		String in = (String) JOptionPane.showInputDialog(null,
				"Who is teaching the course?", 
				"Step 3. Instructor Selection", JOptionPane.QUESTION_MESSAGE,
				null, instr, "Karen Schnell");
		
		// check if the cancel button was clicked
		if (in == null) {
			JOptionPane.showMessageDialog(null, "Exiting course builder.");
			System.exit(0);
		}

		// set Instructor to the validated value
		course.setInstructor(in);
		
		
		// flag whether the file import was successful
		boolean studentFileFound = false;
		// iterator for student count
		int totalStudents = 0;
		
		/**
		 * try to read the file of student names
		 * if the file doesn't exist catch the exception
		 * @exception FileNotFoundException
		 */
		try {
			// create a file object using the intended text file
			File file = new File("students.txt");
			// use the Scanner object to read from the file passing the object
			Scanner readFile = new Scanner(file);
			
			// while the file has a next line, add student name to ArrayList
			while(readFile.hasNextLine()) {
				String line = readFile.nextLine();
				course.getStudents().add(line);
			}
			
			// loop to get the total number of students imported
			for (int i = 0; i < course.getStudents().size(); i++) {
				totalStudents++;
			}
			
			// file was found
			studentFileFound = true;
			
			// close the file
			readFile.close();
			
		} catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	
		// if the file was found ask for remaining student names
		if (studentFileFound) {
			// display total number of students imported if it was successful
			JOptionPane.showMessageDialog(null, String.format("There are %d "
					+ "students in the course.\n Students imported from "
					+ "file: %d", course.MAX_STUDENTS,
					totalStudents), "Student Import Successful",
					JOptionPane.INFORMATION_MESSAGE);
			
			// loop to ask for remaining student averages
			for (int i = totalStudents; i < course.MAX_STUDENTS; i++) {
				// get the student's name
				String stu = (String) JOptionPane.showInputDialog(null,
						String.format("Enter the first name of student %d:", 
								i+1),
						String.format("Step 4. Enter Remaining "
								+ "Students: %d/%d", i+1,
								course.MAX_STUDENTS), 
						JOptionPane.QUESTION_MESSAGE, null, null, "John");
				
				// check if the cancel button was clicked
				if (stu == null) {
					JOptionPane.showMessageDialog(null, "Exiting course "
							+ "builder.");
					System.exit(0);
				}
				
				// always enter loop to catch possible NumberFormatExcpetions
				while(true) {
					/**
					 * try to add stu to ArrayList 
					 * if successful, break from the loop
					 * otherwise, keep asking the user for a valid name
					 * @exception NumberFormatException e checks if stu is a
					 * valid name (doesn't contain numbers)
					 */
					try {
						// call isValidInput to check if the stu string is valid
						if (input.isValidInput(stu)) {
							// add student to course and break from loop
							course.getStudents().add(stu);
							break;
						}
					} catch(NumberFormatException e) {
						System.out.println("Input Error: " + e.getMessage());
					}
					
					// if the input is invalid, ask the user again
					stu = (String) JOptionPane.showInputDialog(null,
							String.format("Please enter a valid first name"
									+ " for student %s:", i+1),
							String.format("Step 4. Enter "
									+ "Students: %d/%d", i+1,
									course.MAX_STUDENTS), 
							JOptionPane.QUESTION_MESSAGE, null, null,
							"John");
					
					// check if the cancel button was clicked
					if (stu == null) {
						JOptionPane.showMessageDialog(null, "Exiting course "
								+ "builder.");
						System.exit(0);
					}
						
				}
			}
			
		} // if the student import was unsuccessful, manually add 25 students
		else {
			// display unsuccessful message
			JOptionPane.showMessageDialog(null, "Student import failed.\n "
					+ String.format("Manually enter the %d students.",
							course.MAX_STUDENTS),
					"Student Import Unsuccessful",
					JOptionPane.INFORMATION_MESSAGE);
			
			// loop to ask for each student name
			for (int i = totalStudents; i < course.MAX_STUDENTS; i++) {
				// get the student's name
				String stu = (String) JOptionPane.showInputDialog(null,
						String.format("Enter the first name of student "
								+ "%d:", i+1),
						String.format("Step 4. Enter "
								+ "Students: %d/%d", i+1,
								course.MAX_STUDENTS), 
						JOptionPane.QUESTION_MESSAGE, null, null, "John");
				
				// check if the cancel button was clicked
				if (stu == null) {
					JOptionPane.showMessageDialog(null, "Exiting course "
							+ "builder.");
					System.exit(0);
				}
				
				// always enter loop to catch possible NumberFormatExcpetions
				while(true) {
					/**
					 * try to add stu to ArrayList 
					 * if successful, break from the loop
					 * otherwise, keep asking the user for a valid name
					 * @exception NumberFormatException e checks if stu is a
					 * valid name (doesn't contain numbers)
					 */
					try {
						// call isValidInput to check if the stu string is valid
						if (input.isValidInput(stu)) {
							// add student to course and break from loop
							course.getStudents().add(stu);
							break;
						}
					} catch(NumberFormatException e) {
						System.out.println("Input Error: " + e.getMessage());
					}
					
					// if the input is invalid, ask the user again
					stu = (String) JOptionPane.showInputDialog(null,
							String.format("Please enter a valid first name"
									+ " for student %d:", i+1),
							String.format("Step 4. Enter "
									+ "Student Names: %d/%d", i+1,
									course.MAX_STUDENTS), 
							JOptionPane.QUESTION_MESSAGE, null, null,
							"John");
					
					// check if the cancel button was clicked
					if (stu == null) {
						JOptionPane.showMessageDialog(null, "Exiting course "
								+ "builder.");
						System.exit(0);
					}
						
				}
			}
			
		}
		
		// determine whether the file import was successful
		boolean averagesFileFound = false;
		// iterator to keep the averages count
		int totalAverages = 0;
		// variable to hold the average input by the user
		double parsedAvg = 0;
		
		/**
		 * try to read the file of student averages
		 * if the file doesn't exist catch the exception
		 * @exception FileNotFoundException display the message stating the
		 * file was not found
		 */
		try {
			// create a file object using the intended text file
			File file = new File("averages.txt");
			// use the Scanner object to read from the file passing the object
			Scanner readFile = new Scanner(file);
			
			// while the file has a next line, add student average to ArrayList
			while(readFile.hasNextLine()) {
				double line = readFile.nextDouble();
				course.getAverages().add(line);
			}
			
			// loop to get the total number of averages imported
			for (int i = 0; i < course.getAverages().size(); i++) {
				totalAverages++;
			}
			
			// set the boolean to true since file was found
			averagesFileFound = true;
			
			// close the file
			readFile.close();
			
		} catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		
		// if the file is found, ask for the remaining averages
		if (averagesFileFound) {
			// display total number of averages imported if it was successful
			JOptionPane.showMessageDialog(null, String.format("There are %d "
					+ "students in the course.\n Averages imported from "
					+ "file: %d", course.MAX_STUDENTS,
					totalAverages), "Averages Import Successful",
					JOptionPane.INFORMATION_MESSAGE);
			
			// loop to ask for remaining averages
			for (int i = totalAverages; i < course.MAX_STUDENTS; i++) {
				// get the student's average
				String avg = (String) JOptionPane.showInputDialog(null,
						String.format("Enter the average for %s%s:", 
					course.getStudents().get(i).substring(0,1).toUpperCase(), 
								course.getStudents().get(i).substring(1)),
						String.format("Step 5. Enter Remaining "
								+ "Averages: %d/%d", i+1,
								course.MAX_STUDENTS), 
						JOptionPane.QUESTION_MESSAGE, null, null, "89");
				
				// check if the cancel button was clicked
				if (avg == null) {
					JOptionPane.showMessageDialog(null, "Exiting course "
							+ "builder.");
					System.exit(0);
				}

				// always enter loop to check if avg can be parsed
				while(true) {
					/**
					 * try to parse avg, if successful, break from the loop
					 * otherwise, keep asking the user for a valid number
					 * @exception NumberFormatException e checks if avg
					 * is a string that can be parsed
					 */
					try {
						// call isValidInput to check if the avg string is valid
						if (input.isValidInput(avg)) {
							// change avg to double and store in new variable
							parsedAvg = Double.parseDouble(avg);
							// add the average to the ArrayList
							course.getAverages().add(parsedAvg);
							break;
						}
					} catch(NumberFormatException e) {
						System.out.println("Input Error: " + e.getMessage());
						
					}
					
					// ask again
					avg = (String) JOptionPane.showInputDialog(null,
							String.format("Please enter a valid average between"
									+ " 0-100 for %s%s:", 
					course.getStudents().get(i).substring(0,1).toUpperCase(), 
									course.getStudents().get(i).substring(1)),
							String.format("Step 5. Enter Remaining "
									+ "Averages: %d/%d", i+1,
									course.MAX_STUDENTS), 
							JOptionPane.QUESTION_MESSAGE, null, null,
							"65");
					
					// check if the cancel button was clicked
					if (avg == null) {
						JOptionPane.showMessageDialog(null, "Exiting course "
								+ "builder.");
						System.exit(0);
					}
				}
			}
		} // if the average import was unsuccessful, manually add 25 averages
		else {
			// display unsuccessful message
			JOptionPane.showMessageDialog(null, "Student averages "
					+ "import failed.\n "
					+ String.format("Manually enter the %d averages.", 
							course.MAX_STUDENTS),
					"Student Averages Import Unsuccessful",
					JOptionPane.INFORMATION_MESSAGE);
			
			// loop to ask for all student averages
			for (int i = totalAverages; i < course.MAX_STUDENTS; i++) {
				// get the each student's average
				String avg = (String) JOptionPane.showInputDialog(null,
						String.format("Enter the average for "
								+ "%s%s:", 
					course.getStudents().get(i).substring(0,1).toUpperCase(), 
								course.getStudents().get(i).substring(1)),
						String.format("Step 5. Enter "
								+ "Student Averages: %d/%d", i+1,
								course.MAX_STUDENTS), 
						JOptionPane.QUESTION_MESSAGE, null, null, "93");
				
				// check if the cancel button was clicked
				if (avg == null) {
					JOptionPane.showMessageDialog(null, "Exiting course "
							+ "builder.");
					System.exit(0);
				}

				// always enter loop to check if avg can be parsed
				while(true) {
					/**
					 * try to parse avg, if successful, break from the loop
					 * otherwise, keep asking the user for a valid number
					 * @exception NumberFormatException e checks if avg
					 * is a string that can be parsed
					 */
					try {
						// call isValidInput to check if the avg string is valid
						if (input.isValidInput(avg)) {
							// change avg to double and store in new variable
							parsedAvg = Double.parseDouble(avg);
							// add the average to the ArrayList
							course.getAverages().add(parsedAvg);
							break;
						}
					} catch(NumberFormatException e) {
						System.out.println("Input Error: " + e.getMessage());
					}
					
					// if input is invalid, ask the user again
					avg = (String) JOptionPane.showInputDialog(null,
							String.format("Please enter a valid average between"
									+ " 0-100 for %s%s:", 
					course.getStudents().get(i).substring(0,1).toUpperCase(), 
									course.getStudents().get(i).substring(1)),
							String.format("Step 5. Enter Remaining "
									+ "Averages: %d/%d", i+1,
									course.MAX_STUDENTS), 
							JOptionPane.QUESTION_MESSAGE, null, null,
							"65");
					
					// check if the cancel button was clicked
					if (avg == null) {
						JOptionPane.showMessageDialog(null, "Exiting course "
								+ "builder.");
						System.exit(0);
					}
				}
			}
		}

		// output to console
		System.out.println(course.toString());
		
	} // end buildCourse

}
