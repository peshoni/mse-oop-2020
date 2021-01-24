package com.edu.oop.patterns.factory;

import com.edu.oop.patterns.IntelectEnchantment;
import com.edu.oop.patterns.Staff;
import com.edu.oop.patterns.StrengthEnchantment;
import com.edu.oop.patterns.Sword;
import com.edu.oop.patterns.Weapon;

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
