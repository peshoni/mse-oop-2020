package exceptions.robot;

import org.junit.Test;

import exceptions.robot.drawers.Drawer;
import exceptions.robot.drawers.SquareDrawerRobot;
import exceptions.robot.figures.Square;
import exceptions.robot.ninja.NoBeepersInBagException;
import kareltherobot.Directions;
import kareltherobot.World;

public class DrawerTest {

	@Test(expected = NoBeepersInBagException.class)
	public void test() {
		World.setSize(10, 10);
		World.setDelay(0);
		Drawer robot = new SquareDrawerRobot(1, 1, Directions.East, 2);
		robot.draw(new Square(5, 5, 4));
	}

}
