package edu.truman.shrestha.toodle_bits;

import java.util.Date;
import java.text.*;

/**
 * @author ds6546
 * 
 */
/**
* A class for dealing with Task
 */
public class Task {

	/** Stores description of the task*/
	private String description;
	/** Stores the ID number of the task*/
	private int identifier;
	/** Stores the status of the task*/
	private String status;
	/** Stores Date task*/
	private Date date_part;
	
	/** Initializing SimpleDateFormat object for converting 
	 * Date object to string and string to date object */
	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Constructs task object that stores information about task
	 * @param description the description of the task being stored.
	 * @param identifier the ID of the task being stored.
	 * @param status the status of the task being stored.
	 * @param dateStr the date of the task being stored.
	 */
	public Task(String description, int identifier, String status,
			String dateStr) {
		//Assignment of constructer's parameter values to the object
		//fields
		this.description = description;
		this.identifier = identifier;
		this.status = status;

		try {
			//Assigns null pointer to date of "Incomplete" object 
			//("Empty string" read from file)
			if (dateStr.equals("")) {
				this.date_part = null;  
			// Assigning Date object for date String read from 
			// file
			} else {
				date_part = formatter.parse(dateStr);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NullPointerException j) {
			j.printStackTrace();
		}
	}

	/**
	 * Constructs task object that stores information about task
	 * @param description the description of the task being stored.
	 * @param identifier the ID of the task being stored.
	 */
	public Task(String description, int identifier) { 
		////Assignment of constructer's parameter values to the object
		//fields
		this.description = description;
		this.identifier = identifier;
		this.status = "Incomplete";
		//Assigns null pointer to date of "Incomplete" object 
		try {
			if (this.status.equals("Incomplete")) {
				// Assigning null pointer to date part.
				this.date_part = null;
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets task ID.
	 * @return returns the identifier number of the
	 * object invoking this method.
	 */
	public int getID() {
		return this.identifier;
	}

	/**
	 * Gets task description.
	 * @return returns the description of the
	 * object invoking this method.
	 */
	public String getdescript() {
		return this.description;
	}

	/**
	 * Gets task status.
	 * @return returns the status of the
	 * object invoking this method .
	 */
	public String getstatus() {
		return this.status;
	}

	/**
	 * Sets task status of the invoking object to complete.
	 */
	public void setstatus() {
		this.status = "Complete";
	}

	/**
	 * Assigns today's date to the date_part field.
	 */
	public void setDate() {
		this.date_part = new Date();
	}

	/**
	 * Gets date of the task object
	 */
	public String getDate() {
		// If the status of task object is incomplete, then return empty
		//string
		if ("Incomplete".equals(getstatus())) {
			return "";
		} else {
			/// If the status of task object is complete, then convert 
			//date_part of the invoking object to string and return it.
			return formatter.format(date_part);
		}
	}

	/**
	 * Prints the Incomplete task.
	 */
	public void printIncompleteTask() {
		System.out.printf("%-5d %-35s %-10s \n", identifier, description,
				status);
	}

	/**
	 * Prints the Complete task.
	 */
	public void printCompleteTask() {
		System.out.printf("%-5d %-35s %-10s %-10s \n", identifier, description,
				status, formatter.format(date_part));
	}

}
