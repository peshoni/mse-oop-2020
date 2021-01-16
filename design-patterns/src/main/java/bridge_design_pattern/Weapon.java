package bridge_design_pattern;

public abstract class Weapon {

	protected Enchantment enchantment;

	public Weapon(Enchantment enchantment) {
		this.enchantment = enchantment;
	}

	public abstract void equip();

	public abstract void swing();

	public abstract void unequip();

	public abstract Enchantment getEnchantment();

}
