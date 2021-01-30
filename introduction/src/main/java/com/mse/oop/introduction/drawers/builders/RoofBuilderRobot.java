package com.mse.oop.introduction.drawers.builders;

import com.mse.oop.introduction.drawers.robots.TriangleDrawerRobot;

/**
 * The roof builder.
 * 
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class RoofBuilderRobot extends TriangleDrawerRobot implements HouseBuilder {

	public RoofBuilderRobot(int street, int avenue, Direction direction, int beepers) {
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
