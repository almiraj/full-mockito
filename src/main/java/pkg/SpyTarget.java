package pkg;

public class SpyTarget {

	public String mainMethod() {
		this.voidMethod("argA");
		return this.returnMethod("argB");
	}

	protected void voidMethod(String arg) {
		this.wrap("a[" + arg + "]");
	}

	protected String returnMethod(String arg) {
		return "b[" + arg + "]";
	}

	protected void wrap(String src) {
		// NOOP
	}

}
