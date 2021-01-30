package com.mse.oop.introduction.drawers.builders;

import com.mse.oop.introduction.drawers.robots.RectangleDrawerRobot;

/**
 * Window and door builder.
 * 
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class WindowAnDoorBuilderRobot extends RectangleDrawerRobot implements HouseBuilder {

	public WindowAnDoorBuilderRobot(int street, int avenue, Direction direction, int beepers) {
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
