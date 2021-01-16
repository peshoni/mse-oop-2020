package bridge_design_pattern;

public class IntelectEnchantment implements Enchantment {

	@Override
	public void onActivate() {
		System.out.println("The enchantment glows in blue");
	}

	@Override
	public void onDeactivate() {
		System.out.println("The enchantment stops glowing in blue");
	}

	@Override
	public void apply() {
		System.out.println("Increase intelect");
	}

}
