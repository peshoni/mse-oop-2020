package com.mse.oop.patterns;

public class Sword extends Weapon {

	public Sword(Enchantment enchantment) {
		super(enchantment);
	}

	@Override
	public void equip() {
		System.out.println("The sword was equiped");
		enchantment.apply();
	}

	@Override
	public void swing() {
		System.out.println("The sword was swinged");
		enchantment.onActivate();
	}

	@Override
	public void unequip() {
		System.out.println("The sword was unequiped");
		enchantment.onDeactivate();
	}

	@Override
	public Enchantment getEnchantment() {
		return enchantment;
	}

}
