/**
 * 
 */
package com.mse.oop.introduction.drawers.builders;

import java.util.ArrayList;
import java.util.List;

import com.mse.oop.introduction.drawers.figures.Figure;
import com.mse.oop.introduction.rightturner.FancyRightTurner;

/**
 * Robot organization class.
 * 
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class HouseBuldersManager {
	private List<HouseBuilder> builders;
	private boolean houseBuilt;

	public HouseBuldersManager() {
		this.builders = new ArrayList<HouseBuilder>();
	}

	/**
	 * Adds builders in collection.
	 * 
	 * @param builder
	 */
	public void addBuilders(HouseBuilder... builder) {
		for (HouseBuilder houseBuilder : builder) {
			builders.add(houseBuilder);
		}
	}

	/**
	 * A method that uses all available builders to build the house.
	 * 
	 */
	public void buildHouse() {
		try {
			builders.forEach(builder -> builder.build());
			houseBuilt = true;
		} catch (Exception e) {
			houseBuilt = false;
		}
	}

	/**
	 * Sends all builders into the house.
	 * 
	 * @param door Through which to enter
	 */
	public void sendTheBuildersIntoTheHouse(Figure door) {
		if (houseBuilt) {
			builders.forEach(robot -> {
				((FancyRightTurner) robot).moveTo(door.getX(), door.getY());
				((FancyRightTurner) robot).setVisible(false);
				((FancyRightTurner) robot).move();
			});
		} else {
			System.err.println("The house is not ready..");
		}
	}

	/**
	 * Getter
	 * 
	 * @return List with {@link HouseBuilder}
	 */
	public List<HouseBuilder> getBuilders() {
		return builders;
	}
}
