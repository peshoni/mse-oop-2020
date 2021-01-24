package com.edu.oop.patterns;

public class Staff extends Weapon {

	public Staff(Enchantment enchantment) {
		super(enchantment);
	}

	@Override
	public void equip() {
		System.out.println("The staff was equiped");
		enchantment.apply();
	}

	@Override
	public void swing() {
		System.out.println("The staff was swinged");
		enchantment.onActivate();
	}

	@Override
	public void unequip() {
		System.out.println("The staff was unequiped");
		enchantment.onDeactivate();
	}

	@Override
	public Enchantment getEnchantment() {
		return enchantment;
	}

}
