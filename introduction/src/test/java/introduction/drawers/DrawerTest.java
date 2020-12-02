package introduction.drawers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import introduction.drawers.figures.Rectangle;
import introduction.drawers.figures.Square;
import introduction.drawers.robots.RectangleDrawerRobot;
import introduction.drawers.robots.SquareDrawerRobot;
import kareltherobot.Directions;
import kareltherobot.World;

public class DrawerTest {

	@Test
	public void testSquareDrawer() {
		World.setSize(10, 10);
		World.setDelay(0);
		SquareDrawerRobot robot = new SquareDrawerRobot(1, 1, Directions.East, 10);
		robot.draw(new Square(2, 2, 2));
		assertEquals(robot.getStreet(), 2);
		assertEquals(robot.getAvenue(), 2);
	}

	@Test
	public void testRectangleDrawer() {
		World.setSize(10, 10);
		World.setDelay(0);
		RectangleDrawerRobot robot = new RectangleDrawerRobot(1, 1, Directions.East, 10);
		robot.draw(new Rectangle(2, 2, 2, 3));
		assertEquals(robot.getStreet(), 2);
		assertEquals(robot.getAvenue(), 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRectangleDrawerWithSquare() {
		World.setSize(10, 10);
		World.setDelay(0);
		RectangleDrawerRobot robot = new RectangleDrawerRobot(1, 1, Directions.East, 10);
		robot.draw(new Square(2, 2, 2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRSquareDrawerWithRectangle() {
		World.setSize(10, 10);
		World.setDelay(0);
		SquareDrawerRobot robot = new SquareDrawerRobot(1, 1, Directions.East, 10);
		robot.draw(new Rectangle(2, 2, 2, 3));
	}

}
