/**
 * 
 */
package bridge_design_pattern;

/**
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class BridgeRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Enchantment intelect = new IntelectEnchantment();
		Enchantment enchantment = new StrengthEnchantment();
		Weapon prinaryWeapon = new Sword(enchantment);
		Weapon secondaryWeapon = new Staff(intelect);

		prinaryWeapon.equip();
		prinaryWeapon.swing();
		prinaryWeapon.unequip();

		System.out.println("-------------------------------------------------------");

		secondaryWeapon.equip();
		secondaryWeapon.swing();
		secondaryWeapon.unequip();

		// Weapon firstWeapon = new Staff(enchantment);

	}

}
