package com.mse.oop.patterns.factory;

import com.mse.oop.patterns.IntelectEnchantment;
import com.mse.oop.patterns.Staff;
import com.mse.oop.patterns.StrengthEnchantment;
import com.mse.oop.patterns.Sword;
import com.mse.oop.patterns.Weapon;

public class SwordFactory {

	public static Weapon build(Weapons sword) {
		if (Weapons.SWORD.equals(sword)) {
			return new Sword(new StrengthEnchantment());
		} else if (Weapons.STAFF.equals(sword)) {
			return new Staff(new IntelectEnchantment());
		}
		return null;

	}

}
