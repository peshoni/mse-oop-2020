package com.mse.oop.exceptions.robot.ninja;

import kareltherobot.Directions;
import kareltherobot.Robot;

/**
 * 
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class AdvancedRobot extends Robot {
	private int street;
	private int avenue;

	/**
	 * Creates new Robot
	 * 
	 * @param street    the starting X
	 * @param avenue    the starting Y
	 * @param direction the direction the robot is initially facing
	 */
	public AdvancedRobot(int street, int avenue, Direction direction) {
		super(street, avenue, direction, 0);
		this.street = street;
		this.avenue = avenue;

	}

	public AdvancedRobot(int street, int avenue, Direction direction, int beepers) {
		super(street, avenue, direction, beepers);
		this.street = street;
		this.avenue = avenue;
	}

	/**
	 * Turns the robot right.
	 */
	public void turnRight() {
		turnLeft();
		turnLeft();
		turnLeft();
	}

	@Override
	public void move() {
		super.move();
		String[] split = this.toString().split(" ");
		String substring = split[4].substring(0, split[4].length() - 1);
		this.street = Integer.parseInt(substring);

		substring = split[6].substring(0, split[6].length() - 1);
		this.avenue = Integer.parseInt(substring);

	}

	public void turnTo(Direction direction) {
		while (direction != getDirection()) {
			turnLeft();
		}
	}

	public void moveTo(int x, int y) {
		// Shift X until robot is positioned
		if (x < getAvenue()) {
			while (!facingWest()) {
				turnLeft();
			}
			while (getAvenue() != x) {
				move();
			}
		} else if (x > getAvenue()) {
			while (!facingEast()) {
				turnLeft();
			}
			while (getAvenue() != x) {
				move();
			}
		}
		// Shift Y until robot is positioned
		if (y > getStreet()) {
			while (!facingNorth()) {
				turnLeft();
			}
			while (getStreet() != y) {
				move();
			}
		} else if (y < getStreet()) {
			while (!facingSouth()) {
				turnLeft();
			}
			while (getStreet() != y) {
				move();
			}
		}
	}

	public Direction getDirection() {
		if (facingWest()) {
			return Directions.West;
		} else if (facingEast()) {
			return Directions.East;
		} else if (facingNorth()) {
			return Directions.North;
		} else if (facingSouth()) {
			return Directions.South;
		}
		return null;
	}

	public int getStreet() {
		return street;
	}

	public void setStreet(int street) {
		this.street = street;
	}

	public int getAvenue() {
		return avenue;
	}

	public void setAvenue(int avenue) {
		this.avenue = avenue;
	}

}
