package com.mse.oop.exceptions.robot.drawers;

import com.mse.oop.exceptions.robot.figures.Figure;
import com.mse.oop.exceptions.robot.figures.Square;
import com.mse.oop.exceptions.robot.ninja.AdvancedRobot;
import com.mse.oop.exceptions.robot.ninja.NoBeepersInBagException;

import kareltherobot.Directions;

public class SquareDrawerRobot extends AdvancedRobot implements Drawer {

	public SquareDrawerRobot(int street, int avenue, Direction direction, int beepers) {
		super(street, avenue, direction, beepers);
		this.setStreet(street);
		this.setAvenue(avenue);
	}

	/**
	 * Draws a square. Checks the amount of beepеr's and if they run out - throws an
	 * {@link NoBeepersInBagException}
	 * 
	 * @param {@link {@link Figure}
	 */
	public void draw(Figure figure) {
		if (!(figure instanceof Square)) {
			throw new IllegalArgumentException("Unsupported parameter type");
		}
		Square square = (Square) figure;

		moveTo(figure.getX(), figure.getY());
		turnTo(Directions.East);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < square.getWidth(); j++) {

				if (!this.anyBeepersInBeeperBag()) {
					throw new NoBeepersInBagException("Out of beepers.");
				}

				super.putBeeper();
				move();
			}
			turnLeft();
		}
		turnRight();
	}
}
