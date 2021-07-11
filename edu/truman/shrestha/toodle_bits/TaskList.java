/**
 * 
 */
package edu.truman.shrestha.toodle_bits;

import java.util.ArrayList;
import edu.truman.shrestha.toodle_bits.Task;

/**
 * @author ds6546
 * 
 */
/**
 * A class for storing list of Tasks.
 */
public class TaskList {
	/** Initializes an array list for storing Tasks. */
	private ArrayList<Task> TaskArray = new ArrayList<Task>();
	/** Variable for storing size of the array list. */
	private int arrayListSize = TaskArray.size();
	/** Array list for storing only Task IDs */
	private ArrayList<Integer> IDArray = new ArrayList<Integer>();

	/**
	 * Constructs a Default TaskList object that 
	 * stores and modifies Tasks
	 */
	public void TaskList() {

	}

	/**
	 * Gets the size of array.
	 * 
	 * @return the array size.
	 */
	public int getArraySize() {
		return this.arrayListSize;
	}

	/**
	 * Sets the array size
	 */
	public void setArraySize() {
		arrayListSize = this.TaskArray.size();
	}

	/**
	 * Sets Ids from task into array.
	 */
	public void setIdIntoArray() {
		setArraySize();
		int j;
		// Extracts IDs from the Task objects in TaskList
		// object and stores it in IDArray
		for (j = 0; j < arrayListSize; j++) {
			Task reference = this.TaskArray.get(j);
			int intermediateId = reference.getID();
			IDArray.add(j, intermediateId);
		}
	}

	/**
	 * Finds the ID for the next task to be created.
	 * 
	 * @return unique ID that is to be assigned to the next task.
	 */
	public int findNextID() { // variable for storing max number
		int maxNum = this.IDArray.get(0);
		// loops through ID Array to find the maximum
		// number
		for (int i = 0; i < (this.IDArray.size()); i++) {
			if (IDArray.get(i) > maxNum) {
				maxNum = IDArray.get(i);
			}
		}
		// returns number greater than the maximum
		// number in the ID Array.
		return (maxNum + 1);
	}

	/**
	 * Method for creating a new task.
	 * 
	 * @param s
	 *            the task description of the task being created.
	 */
	public void createTask(String s) {
		// Calls the Task constructor to create a task
		// object and add it to the TaskArray.
		this.TaskArray.add(new Task(s, findNextID()));
	}

	/**
	 * Method for creating a new task.
	 * 
	 * @param s
	 *            the task description of the task being created.
	 * @param d
	 *            the ID for new task being created.
	 */
	public void createTask(String s, int d) {
		this.TaskArray.add(new Task(s, d));
	}

	/**
	 * Add task to the Array list of TaskList object.
	 * 
	 * @param n
	 *            the task ID that gets added to the list.
	 */
	public void addTask(Task n) {
		TaskArray.add(n);
	}

	/**
	 * Method for creating a new task.
	 * 
	 * @param description
	 *            the task description
	 * @param ID
	 *            the ID of the new task.
	 * @param status
	 *            the status of the new task.
	 * @param date_from_file
	 *            the date of completion of the task.
	 */
	public void createsTask(String description, int ID, String status,
			String date_from_file) {
		Task object = new Task(description, ID, status, date_from_file);
		TaskArray.add(object);
	}

	/**
	 * Prints all the incomplete Task objects.
	 */
	public void printAllIncompleteTasks() {
		System.out.printf("%-5s %-35s %-10s %-10s \n", "ID", "Task", "Status",
				"Date");
		System.out.printf("%-5s %-35s %-10s %-10s \n", "--", "----", "------",
				"-----");
		int j = 0;
		// Looping through TaskArray to call printIncompleteTask
		// method on task objects with "Incomplete" status.
		for (j = 0; j < TaskArray.size(); j++) {
			Task model = TaskArray.get(j);
			if (model.getstatus().equals("Incomplete")) {
				model.printIncompleteTask();
			}
		}
	}

	/**
	 * Prints all the Task objects.
	 */
	public void printAllTasks() {
		System.out.printf("%-5s %-35s %-10s %-10s \n", "ID", "Task", "Status",
				"Date");
		System.out.printf("%-5s %-35s %-10s %-10s \n", "--", "----", "------",
				"-----");
		int i = 0;
		// Looping through TaskArray to call printIncompleteTask
		// method on task objects with "Incomplete" status and printCompleteTask
		// method on task objects with "Complete" status.
		for (i = 0; i < TaskArray.size(); i++) {
			Task model = TaskArray.get(i);
			if (model.getstatus().equals("Complete")) {
				model.printCompleteTask();
			} else {
				model.printIncompleteTask();
			}
		}
	}

	/**
	 * Mark the given ID's task as complete and assigns completion date.
	 * 
	 * @param x
	 *            the ID number of the Task object whose status is changed to
	 *            complete and completion date is assigned.
	 */
	public void markComplete(int x) {
		int j = 0;
		// Looping through each Task object in the array list.
		for (j = 0; j < arrayListSize; j++) {
			Task model = TaskArray.get(j);
			// If id number matches, changes the status.
			if (model.getID() == x) {
				model.setstatus();
				model.setDate();
				System.out
				.println("--- Task " + x + " marked as complete. ---");
				return; // breaks the loop.
			}
		}
	}

	/**
	 * Gets ID of the task object.
	 * 
	 * @param i
	 *            the index of the Task object in the array.
	 * @return ID of the task object present in the specified index in the form
	 *         of string.
	 */
	public String getID(int i) {
		return Integer.toString(TaskArray.get(i).getID());
	}

	/**
	 * Gets Description of the task object.
	 * 
	 * @param i
	 *            the index of the Task object in the array.
	 * @return description of the task object present in the specified index in
	 *         the form of string.
	 */
	public String getDescription(int i) {
		return TaskArray.get(i).getdescript();
	}

	/**
	 * Gets Status of the task object.
	 * 
	 * @param i
	 *            the index of the Task object in the array.
	 * @return status of the task object present in the specified index in the
	 *         form of string.
	 */
	public String getStat(int i) {
		return TaskArray.get(i).getstatus();
	}

	/**
	 * Gets Date of completion of the task object.
	 * 
	 * @param i
	 *            the index of the Task object in the array.
	 * @return Date of the task object present in the specified index in the
	 *         form of string.
	 */
	public String getDat(int i) {
		return TaskArray.get(i).getDate();
	}

}
