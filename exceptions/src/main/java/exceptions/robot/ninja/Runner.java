package exceptions.robot.ninja;

import exceptions.robot.drawers.Drawer;
import exceptions.robot.drawers.SquareDrawerRobot;
import exceptions.robot.figures.Square;
import kareltherobot.Directions;
import kareltherobot.World;

public class Runner {
	public static void main(String[] args) {

		World.setSize(10, 10);
		World.setDelay(0);
		Drawer robot = new SquareDrawerRobot(2, 2, Directions.East, 2);

		robot.draw(new Square(2, 2, 4));

	}
}
