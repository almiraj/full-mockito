package pkg;

public class MockTarget {

	// will be mocked
	private MockTargetBean bean;

	protected String mainMethod() {
		return this.bean.getSomething();
	}

}
