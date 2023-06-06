package pkg;

public class SpyTarget {

	public String mainMethod() {
		this.voidMethod("argA");
		return this.returnMethod("argB");
	}

	protected void voidMethod(String arg) {
		// NOOP
	}

	protected String returnMethod(String arg) {
		return "b[" + arg + "]";
	}

}
