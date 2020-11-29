package introduction.rightturner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import kareltherobot.Directions;
import kareltherobot.World;

public class RightTurnerTest {
	@Test
	public void testTurnRight() {
		World.setSize(1, 1);
		World.setDelay(0);
		FancyRightTurner robot = new FancyRightTurner(1, 1, Directions.East);
		robot.turnRight();
		assertTrue(robot.facingSouth());
	}

	@Test
	public void testFancyTurnRight() {
		World.setSize(3, 3);
		World.setDelay(0);
		FancyRightTurner robot = new FancyRightTurner(1, 1, Directions.East);
		robot.fancyTurnRight();
		assertTrue(robot.facingSouth());
	}

	@Test
	public void testMoveTo() {
		World.setSize(10, 10);
		World.setDelay(0);
		FancyRightTurner robot = new FancyRightTurner(1, 1, Directions.East);
		robot.moveTo(3, 3);
		assertEquals(3, robot.getStreet());
		assertEquals(3, robot.getAvenue());
		robot.moveTo(1, 1);
		assertEquals(1, robot.getStreet());
		assertEquals(1, robot.getAvenue());
		robot.moveTo(4, 3);
		assertEquals(3, robot.getStreet());
		assertEquals(4, robot.getAvenue());
	}

	@Test
	public void testTurnTo() {
		World.setSize(5, 5);
		World.setDelay(0);
		FancyRightTurner robot = new FancyRightTurner(1, 1, Directions.East);
		robot.turnTo(Directions.North);
		assertTrue(robot.facingNorth());
	}

}
