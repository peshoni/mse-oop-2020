package introduction.drawers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import introduction.figures.Rectangle;
import introduction.figures.Square;
import kareltherobot.Directions;
import kareltherobot.World;

public class DrawerTest {
	@Test
	public void testSquareDrawer() {
		World.setSize(10, 10);
		World.setDelay(0);
		SquareDrawerRobot robot = new SquareDrawerRobot(1, 1, Directions.East, 10);
		robot.draw(new Square(1, 1, 2));
		assertEquals(robot.getStreet(), 1);
		assertEquals(robot.getAvenue(), 1);
	}

	@Test
	public void testRectangleDrawer() {
		World.setSize(10, 10);
		World.setDelay(0);
		RectangleDrawerRobot robot = new RectangleDrawerRobot(1, 1, Directions.East, 10);
		robot.draw(new Rectangle(1, 1, 2, 3));
		assertEquals(robot.getStreet(), 1);
		assertEquals(robot.getAvenue(), 1);
	}

}
