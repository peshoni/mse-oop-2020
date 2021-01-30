/**
 * 
 */
package com.mse.oop.exceptions.robot.ninja;

/**
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class EncounteredWallException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -36531347288653440L;

	public EncounteredWallException(String message) {
		super(message);
	}

	public EncounteredWallException(String message, Throwable e) {
		super(message, e);
	}

}
