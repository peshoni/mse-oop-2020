/**
 * 
 */
package com.mse.oop.introduction.rightturner;

import com.mse.oop.introduction.drawers.builders.HouseBuldersManager;
import com.mse.oop.introduction.drawers.builders.MasonRobot;
import com.mse.oop.introduction.drawers.builders.RoofBuilderRobot;
import com.mse.oop.introduction.drawers.builders.WindowAnDoorBuilderRobot;
import com.mse.oop.introduction.drawers.figures.Rectangle;
import com.mse.oop.introduction.drawers.figures.Triangle;

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
		World.setSize(25, 40);
		World.setVisible();
		World.setDelay(3);

		HouseBuldersManager builderManager = new HouseBuldersManager();
		MasonRobot masonRobot = new MasonRobot(1, 5, Directions.North, 60);
		WindowAnDoorBuilderRobot windowRobot = new WindowAnDoorBuilderRobot(1, 4, Directions.North, 60);
		WindowAnDoorBuilderRobot doorRobot = new WindowAnDoorBuilderRobot(1, 3, Directions.North, 60);
		WindowAnDoorBuilderRobot seconmdWindowRobot = new WindowAnDoorBuilderRobot(1, 2, Directions.North, 60);
		RoofBuilderRobot roofBuilder = new RoofBuilderRobot(1, 1, Directions.North, 60);

		Rectangle mason = new Rectangle(7, 1, 20, 10);
		Rectangle window = new Rectangle(9, 5, 4, 4);
		Rectangle door = new Rectangle(15, 1, 4, 8);
		Rectangle secondWindow = new Rectangle(21, 5, 4, 4);
		Triangle roof = new Triangle(4, 11, mason.getWidth() + 6, 13);

		masonRobot.setTask(mason);
		windowRobot.setTask(window);
		doorRobot.setTask(door);
		seconmdWindowRobot.setTask(secondWindow);
		roofBuilder.setTask(roof);

		builderManager.addBuilders(masonRobot, windowRobot, doorRobot, seconmdWindowRobot, roofBuilder);
		builderManager.buildHouse();
		builderManager.sendTheBuildersIntoTheHouse(door);
	}
}
