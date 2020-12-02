package introduction.drawers.robots;

import introduction.drawers.figures.Figure;
import introduction.drawers.figures.Triangle;
import introduction.rightturner.FancyRightTurner;
import kareltherobot.Directions;

public class TriangleDrawerRobot extends FancyRightTurner implements Drawer {

	public TriangleDrawerRobot(int street, int avenue, Direction direction, int beepers) {
		super(street, avenue, direction, beepers);
	}

	public void draw(Figure figure) {

		if (!(figure instanceof Triangle)) {
			throw new IllegalArgumentException("Unsupported parameter type");
		}
		Triangle triangle = (Triangle) figure;

		moveTo(triangle.getX(), triangle.getY());
		turnTo(Directions.East);

		for (int i = 0; i <= triangle.getWidth(); i++) {
			super.putBeeper();
			move();
		}
		moveTo(getAvenue() - 1, getStreet());
		int center = (int) triangle.getWidth() / 2;
		for (int i = 0; i < triangle.getWidth(); i++) {
			moveTo(getAvenue() - 1, getStreet() + (i < center ? 1 : -1));
			super.putBeeper();
		}
	}
}
