package com.mse.oop.exceptions.robot.ninja;

import com.mse.oop.exceptions.robot.figures.Square;
import com.mse.oop.exceptions.robot.superrobot.SuperRobot;

import kareltherobot.Directions;
import kareltherobot.World;

public class RunnerNinja {
	public static void main(String[] args) {

		World.setSize(10, 12);
		World.setVisible();
		World.setDelay(5);
//		Drawer robot = new SquareDrawerRobot(2, 2, Directions.East, 2); 
//		robot.draw(new Square(2, 2, 4)); 

		SuperRobot superRobot = new SuperRobot(2, 2, Directions.North, 2);
		superRobot.setMaxStree(10);
		superRobot.setMaxAvenue(12);
		superRobot.draw(new Square(8, 8, 4));

	}
}
