/**
 * 
 */
package introduction;

import kareltherobot.Directions;
import kareltherobot.World;

/**
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class Runner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		World.setSize(5, 5);
		World.setVisible();
		FancyRightTurner robot = new FancyRightTurner(1, 1, Directions.East);
		robot.move();
		robot.turnLeft();
		robot.move();
		robot.turnLeft();
		robot.move();
		robot.turnLeft();
		robot.move();

	}

}
