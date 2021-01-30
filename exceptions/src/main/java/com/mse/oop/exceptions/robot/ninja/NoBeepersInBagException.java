/**
 * 
 */
package com.mse.oop.exceptions.robot.ninja;

/**
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class NoBeepersInBagException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -36531347288653440L;

	public NoBeepersInBagException(String message) {
		super(message);
	}

	public NoBeepersInBagException(String message, Throwable e) {
		super(message, e);
	}

}
