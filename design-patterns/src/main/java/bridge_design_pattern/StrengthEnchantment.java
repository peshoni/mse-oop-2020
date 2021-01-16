package bridge_design_pattern;

public class StrengthEnchantment implements Enchantment {

	@Override
	public void onActivate() {
		System.out.println("The enchantment glows in red (strength)");
	}

	@Override
	public void onDeactivate() {
		System.out.println("The enchantment stops glowing in red (strength)");
	}

	@Override
	public void apply() {
		System.out.println("Increase strength");
	}

}
