package design_patterns.factory;

import bridge_design_pattern.IntelectEnchantment;
import bridge_design_pattern.Staff;
import bridge_design_pattern.StrengthEnchantment;
import bridge_design_pattern.Sword;
import bridge_design_pattern.Weapon;

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
