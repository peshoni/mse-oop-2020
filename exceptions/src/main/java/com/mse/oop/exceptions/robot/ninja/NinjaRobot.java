/**
 * 
 */
package com.mse.oop.exceptions.robot.ninja;

/**
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class NinjaRobot extends AdvancedRobot {

	public NinjaRobot(int street, int avenue, Direction direction) {
		super(street, avenue, direction);
	}

	public NinjaRobot(int street, int avenue, Direction direction, int beepers) {
		super(street, avenue, direction, beepers);
	}

	/**
	 * Moves forward, avoids wall.
	 */
	@Override
	public void move() {
		if (!this.frontIsClear()) {
			throw new EncounteredWallException("Was facing wall, stopping the action.");
		}
		super.move();
	}

}
