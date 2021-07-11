/**
 * 
 */
/* Deepson Shrestha ************ CS 260 ********** Project 1************/

import java.util.*;
import java.io.*;
import edu.truman.shrestha.toodle_bits.TaskList;

/**
 * @author ds6546
 * 
 */

/**
 * Main class.
 */
public class ShresthaToodle {

	/**
	 * Main method.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		TaskList obj = new TaskList();
		// Storing first Next ID from file.
		int requiredId = readFile(obj);

		Scanner keyboard = new Scanner(System.in);
		Scanner keyboardt = new Scanner(System.in);

		// Variable for determining if it's
		// the first time the task is created.
		int counter = 0;

		while (true) {
			System.out.print("\nPLease enter a command (1-6; 6 for help): ");
			int command = keyboard.nextInt();

			try {
				// prints menu.
				if (command == 6) {
					printMenu();
				}

				// Creating a new task. If it is the first task, next ID from
				// the
				// first line of file is used. Else, findNextID () method is run
				// to get a unique ID.
				if (command == 1) {
					System.out.println("Enter task description: ");
					String task_description = keyboardt.nextLine();
					if (counter == 0) {
						obj.createTask(task_description, requiredId);
						System.out.println("--Task Created: ID is "
								+ requiredId + "--");
						counter = counter + 1;
					} else {
						obj.createTask(task_description);
						System.out.println("--Task Created: ID is "
								+ obj.findNextID() + "--");
					}

					obj.setIdIntoArray();
				}

				// Prints Incomplete task.
				if (command == 2) {
					obj.printAllIncompleteTasks();
				}

				// Prints all task.
				if (command == 3) {
					obj.printAllTasks();
				}

				// Reads input and marks the task specified by the input ID as
				// complete.
				if (command == 4) {
					System.out.println("Enter ID number of task: ");
					int idMarkComplete = keyboard.nextInt();
					obj.markComplete(idMarkComplete);
				}

				// Saves the task and exits.
				if (command == 5) {
					System.out.println("Saving task list ...");
					System.out.println("\n\nGoodbye");
					writeFile(obj);
				}

			} catch (InputMismatchException e) {
				System.out
				.println("Please restart the program & give valid input");
			}
		}

	}

	/**
	 * Reads the file "task_list.txt".
	 * 
	 * @param obj
	 *            the task list into which the tasks read from the file is
	 *            stored.
	 * @return the next integer ID if the file exists or return "1" as the ID
	 *         for the first task if the file does not exist.
	 */
	public static int readFile(TaskList obj) {

		int Iden = 0;
		// Initializing file object.
		File theFile = new File("task_list.txt");
		// Checking if the file exists.
		boolean exists = theFile.exists();

		if (exists) {
			try {
				// Initializing FileReader object.
				FileReader filestream = new FileReader(theFile);
				// Associating FileReader object with Scanner.
				Scanner file = new Scanner(filestream);
				// Storing the first integer data from file
				// into firstFileID.
				int firstFileId = file.nextInt();
				Iden = firstFileId;
				// Storing the second integer data from file
				// into total_tasks.
				int total_tasks = file.nextInt();

				// Loads the data from file to an arraylist of TaskList object.
				for (int i = 0; (i <= total_tasks) && (file.hasNextInt()); i++) {
					int ID = file.nextInt();
					String dummy = file.nextLine();
					String description = file.nextLine();
					String status = file.nextLine();
					String date_from_file = file.nextLine();
					obj.createsTask(description, ID, status, date_from_file);
				}
			}

			catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("Attempt to read the file has failed");
			}

		} else if (!exists) {
			try {
				// Creating Printwriter object for creating new file
				PrintWriter writer = new PrintWriter(theFile);
				// Writing Next Id in first line of file
				writer.println("1");
				// Writing Total tasks in the second line of file
				writer.println("0");
				// Returns next id.
				Iden = 1;
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Attempt to read the file has failed");
			}
		}
		// Method for setting Tasks array size into a variable.
		obj.setArraySize();
		// Method for setting IDs into an arraylist.
		obj.setIdIntoArray();
		// Returns next ID.
		return Iden;

	}

	/**
	 * Print program functions to the user.
	 */
	public static void printMenu() {
		System.out.println(" 1 - Create a new task");
		System.out.println(" 2 - Print all incomplete tasks");
		System.out.println(" 3 - Print all tasks");
		System.out.println(" 4 - Mark a task as complete");
		System.out.println(" 5 - Save the task list and exit the program");
		System.out.println(" 6 - Print this menu\n");
	}

	/**
	 * Writes the content from TaskList array to file.
	 * 
	 * @param obj
	 *            the task list from which the tasks are read into the file.
	 */
	public static void writeFile(TaskList obj) {
		// Initializing fileWriter object.
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("task_list.txt");
			// Using findNextID method on TaskList object to store the next
			// ID in first Line of file.
			int firstLine = obj.findNextID();
			fileWriter.write(Integer.toString(firstLine) + "\n");
			// Using getArraySIze method on TaskList object to store the
			// TaskList object in second Line of file.
			fileWriter.write(Integer.toString(obj.getArraySize()) + "\n");
			// Writing from TaskList object's arraylist into file.
			for (int i = 0; i < obj.getArraySize(); i++) {
				fileWriter.write(obj.getID(i) + "\n");
				fileWriter.write(obj.getDescription(i) + "\n");
				fileWriter.write(obj.getStat(i) + "\n");
				if (obj.getStat(i).equals("Incomplete")) {
					fileWriter.write("\n");
				} else {
					fileWriter.write(obj.getDat(i) + "\n");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Closing the file
				if (fileWriter != null) {
					fileWriter.flush();
					fileWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.exit(0);
		return;
	}
}
