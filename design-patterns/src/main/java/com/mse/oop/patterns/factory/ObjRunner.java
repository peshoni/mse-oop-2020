/**
 * 
 */
package com.mse.oop.patterns.factory;

import com.mse.oop.patterns.Weapon;

/**
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class ObjRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Weapon weapon = SwordFactory.build(Weapons.SWORD);
		weapon.equip();
		weapon.swing();
		weapon.unequip();
	}

}
