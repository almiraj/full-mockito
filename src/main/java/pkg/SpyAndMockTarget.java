package pkg;

public class SpyAndMockTarget {

	// will be mocked
	private MockTargetBean bean;

	public String mainMethod() {
		this.voidMethod(this.bean.getSomething());
		return this.returnMethod("argB");
	}

	protected void voidMethod(String arg) {
		// NOOP
	}

	protected String returnMethod(String arg) {
		return "b[" + arg + "]";
	}
}
