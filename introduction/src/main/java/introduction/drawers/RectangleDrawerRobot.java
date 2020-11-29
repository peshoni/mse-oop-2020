package introduction.drawers;

import introduction.FancyRightTurner;
import introduction.figures.Figure;
import introduction.figures.Rectangle;
import kareltherobot.Directions;

public class RectangleDrawerRobot extends FancyRightTurner implements Drawer {

	public RectangleDrawerRobot(int street, int avenue, Direction direction, int beepers) {
		super(street, avenue, direction, beepers);
	}

	public void draw(Figure figure) {

		if (!(figure instanceof Rectangle)) {
			System.err.println("Unsupported parameter type");
			return;
		}
		Rectangle rectangle = (Rectangle) figure;

		moveTo(figure.getX(), figure.getY());
		turnTo(Directions.East);

		for (int i = 0; i < 4; i++) {
			int size = i % 2 == 0 ? rectangle.getX() : rectangle.getY();

			for (int j = 0; j < size; j++) {
				super.putBeeper();
				move();
			}
			turnLeft();
		}
		turnRight();

	}
}
