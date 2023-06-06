package pkg.statics;

public class StaticStatefulClass {

	private static int counter = 100;

	synchronized public static int increment() {
		counter++;
		return counter;
	}

}
