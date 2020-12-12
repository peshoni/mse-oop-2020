/**
 * 
 */
package design_patterns;

/**
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public final class SingletonDemo {
	private static SingletonDemo singleton = new SingletonDemo();

	private SingletonDemo() {
		// hide constructor
	}

	public static SingletonDemo instance() {
		return singleton;
	}
}
