/**
 * 
 */
package com.mse.oop.introduction.drawers.builders;

/**
 * Custom exception that occurs when the task of robot wasn't set.
 * 
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class MissingTaskException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4797114090919224652L;

	/**
	 * Constructor for the exception using message.
	 * 
	 * @param message The message of the exception.
	 */
	public MissingTaskException(String message) {
		super(message);
	}

}
