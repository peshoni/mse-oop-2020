/**
 * 
 */
package exceptions.robot.superrobot;

import exceptions.robot.drawers.Drawer;
import exceptions.robot.figures.Figure;
import exceptions.robot.figures.Square;
import exceptions.robot.ninja.AdvancedRobot;
import exceptions.robot.ninja.EncounteredWallException;
import exceptions.robot.ninja.NoBeepersInBagException;
import kareltherobot.Directions;

/**
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class SuperRobot extends AdvancedRobot implements Drawer {
	private int maxStree;
	private int maxAvenue;

	public SuperRobot(int street, int avenue, Direction direction, int beepers) {
		super(street, avenue, direction, beepers);
	}

	public void draw(Figure figure) {
		if (!(figure instanceof Square)) {
			throw new IllegalArgumentException("Unsupported parameter type");
		}
		Square square = (Square) figure;

		moveTo(figure.getX(), figure.getY());
		turnTo(Directions.East);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < square.getWidth(); j++) {
				try {
					if (!this.anyBeepersInBeeperBag()) {
						throw new NoBeepersInBagException("Out of beepers.");
					}
					super.putBeeper();
				} catch (NoBeepersInBagException e) {
					System.out.println("Out of beepers");
				}
				try {
					move();
				} catch (Exception e) {
					System.out.println(e.getMessage());
					// reduce square and turn left
				}
			}
			turnLeft();
		}
		turnRight();
	}

	/**
	 * Moves forward, avoids wall. When is wall faced - reduces the square sides
	 */
	@Override
	public void move() {
		if (!this.frontIsClear() || isRobotOutOfWorld()) {
			throw new EncounteredWallException("Was facing wall, stopping the action.");
		}
		super.move();
	}

	private boolean isRobotOutOfWorld() {
		return (getStreet() + 1 == getMaxStree() || getAvenue() + 1 == getMaxAvenue());
	}

	public int getMaxStree() {
		return maxStree;
	}

	public void setMaxStree(int maxStree) {
		System.out.println("set str: " + maxStree);
		this.maxStree = maxStree;
	}

	public int getMaxAvenue() {
		return maxAvenue;
	}

	public void setMaxAvenue(int maxAvenue) {
		System.out.println("set ave: " + maxAvenue);
		this.maxAvenue = maxAvenue;
	}

}
