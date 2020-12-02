/**
 * 
 */
package introduction.drawers.builders;

import introduction.drawers.robots.RectangleDrawerRobot;

/**
 * The mason robot.
 * 
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class MasonRobot extends RectangleDrawerRobot implements HouseBuilder {

	public MasonRobot(int street, int avenue, Direction direction, int beepers) {
		super(street, avenue, direction, beepers);
	}

	public void build() {
		if (getTask() != null) {
			moveTo(getTask().getX(), getTask().getY());
			draw(getTask());
			goToStartingPosition();
		} else {
			throw new MissingTaskException("Missing task.");
		}
	}
}
