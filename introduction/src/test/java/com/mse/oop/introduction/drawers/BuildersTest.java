package com.mse.oop.introduction.drawers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mse.oop.introduction.drawers.builders.HouseBuldersManager;
import com.mse.oop.introduction.drawers.builders.MasonRobot;
import com.mse.oop.introduction.drawers.builders.MissingTaskException;
import com.mse.oop.introduction.drawers.builders.RoofBuilderRobot;
import com.mse.oop.introduction.drawers.builders.WindowAnDoorBuilderRobot;
import com.mse.oop.introduction.drawers.figures.Rectangle;
import com.mse.oop.introduction.drawers.figures.Triangle;
import com.mse.oop.introduction.rightturner.FancyRightTurner;

import kareltherobot.Directions;
import kareltherobot.World;

public class BuildersTest {

	@Test
	public void BuildMasonWithMasonRobotTest() {
		World.setSize(10, 10);
		World.setDelay(0);
		MasonRobot masonRobot = new MasonRobot(1, 5, Directions.North, 60);
		Rectangle mason = new Rectangle(7, 1, 20, 10);
		masonRobot.setTask(mason);
		masonRobot.build();
		assertEquals(masonRobot.getOriginX(), masonRobot.getAvenue());
		assertEquals(masonRobot.getOriginY(), masonRobot.getStreet());
	}

	@Test
	public void BuildWindowWithWindowBuilderTestRobot() {
		World.setSize(10, 10);
		World.setDelay(0);
		WindowAnDoorBuilderRobot windowRobot = new WindowAnDoorBuilderRobot(1, 4, Directions.North, 60);
		Rectangle window = new Rectangle(9, 5, 4, 4);
		windowRobot.setTask(window);
		windowRobot.build();
		assertEquals(windowRobot.getOriginX(), windowRobot.getAvenue());
		assertEquals(windowRobot.getOriginY(), windowRobot.getStreet());
	}

	@Test(expected = MissingTaskException.class)
	public void TryToBuildMasonWithoutTaskTest() {
		MasonRobot robot = new MasonRobot(1, 4, Directions.North, 60);
		robot.build();
	}

	@Test(expected = MissingTaskException.class)
	public void TryToBuildWindowWithoutTaskTest() {
		WindowAnDoorBuilderRobot robot = new WindowAnDoorBuilderRobot(1, 4, Directions.North, 60);
		robot.build();
	}

	@Test(expected = MissingTaskException.class)
	public void TryToBuildRoofWithoutTaskTest() {
		new RoofBuilderRobot(1, 4, Directions.North, 60).build();
	}

	@Test
	public void BuildDoorWithWindowBuilderRobotTest() {
		World.setSize(10, 10);
		World.setDelay(0);
		WindowAnDoorBuilderRobot doorRobot = new WindowAnDoorBuilderRobot(1, 3, Directions.North, 60);
		Rectangle door = new Rectangle(15, 1, 4, 8);
		doorRobot.setTask(door);
		doorRobot.build();
		assertEquals(doorRobot.getOriginX(), doorRobot.getAvenue());
		assertEquals(doorRobot.getOriginY(), doorRobot.getStreet());
	}

	@Test
	public void BuildRoofWithRoofBuilderRobotTest() {
		World.setSize(10, 10);
		World.setDelay(0);
		RoofBuilderRobot roofBuilder = new RoofBuilderRobot(1, 1, Directions.North, 60);
		Triangle roof = new Triangle(4, 11, 26, 13);
		roofBuilder.setTask(roof);
		roofBuilder.build();
		assertEquals(roofBuilder.getOriginX(), roofBuilder.getAvenue());
		assertEquals(roofBuilder.getOriginY(), roofBuilder.getStreet());
	}

	@Test
	public void HouseBuilderManagerTest() {
		World.setSize(25, 40);
		World.setDelay(0);

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

		builderManager.getBuilders().stream().filter(FancyRightTurner.class::isInstance)
				.map(FancyRightTurner.class::cast).forEach(builder -> {
					assertEquals(builder.getOriginX(), builder.getAvenue());
					assertEquals(builder.getOriginY(), builder.getStreet());
				});
		builderManager.sendTheBuildersIntoTheHouse(door);

		builderManager.getBuilders().stream().filter(FancyRightTurner.class::isInstance)
				.map(FancyRightTurner.class::cast).forEach(builder -> {
					assertEquals(builder.getAvenue(), door.getX() + 1);
					assertEquals(builder.getStreet(), door.getY());
				});

	}

}
