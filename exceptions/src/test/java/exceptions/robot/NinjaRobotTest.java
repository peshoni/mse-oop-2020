package exceptions.robot;

import org.junit.Test;

import exceptions.robot.ninja.EncounteredWallException;
import exceptions.robot.ninja.NinjaRobot;
import kareltherobot.Directions;
import kareltherobot.World;

public class NinjaRobotTest {

	@Test(expected = EncounteredWallException.class)
	public void test() {
		World.setSize(10, 10);
		World.setDelay(0);
		NinjaRobot robot = new NinjaRobot(1, 1, Directions.West);
		robot.move();
	}

}
