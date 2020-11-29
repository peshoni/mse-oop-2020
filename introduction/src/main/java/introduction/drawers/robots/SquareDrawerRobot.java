package introduction.drawers.robots;

import introduction.drawers.figures.Figure;
import introduction.drawers.figures.Rectangle;
import introduction.drawers.figures.Square;
import introduction.rightturner.FancyRightTurner;
import kareltherobot.Directions;

public class SquareDrawerRobot extends FancyRightTurner implements Drawer {

	public SquareDrawerRobot(int street, int avenue, Direction direction, int beepers) {
		super(street, avenue, direction, beepers);
		this.setStreet(street);
		this.setAvenue(avenue);
	}

	/**
	 * Draws a square from lower left corner
	 * 
	 * @param sideLength the length of side
	 */

	public void draw(Figure figure) {
		if (!(figure instanceof Rectangle)) {
			throw new IllegalArgumentException("Unsupported parameter type");
		}
		Square square = (Square) figure;

		moveTo(figure.getX(), figure.getY());
		turnTo(Directions.East);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < square.getWidth(); j++) {
				super.putBeeper();
				move();
			}
			turnLeft();
		}
		turnRight();

	}

}
