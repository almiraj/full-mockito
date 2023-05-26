package pkg;

public class BasicTarget {

	@SuppressWarnings("unused")
	private static final String PRIVATE_STATIC_FINAL_FIELD_VALUE = "PRIVATE_STATIC_FINAL_FIELD_VALUE!";

	@SuppressWarnings("unused")
	private static String PRIVATE_STATIC_FIELD_VALUE = "PRIVATE_STATIC_FIELD_VALUE!";

	@SuppressWarnings("unused")
	private String privateFieldValue = "privateFieldValue!";

	public String basicTarget() {
		StringBuilder sb = new StringBuilder();

		this.publicVoidMethod("publicVoidMethodArg");
		sb.append(this.publicReturnMethod("publicReturnMethodArg"));

		this.privateVoidMethod("privateVoidMethodArg");
		sb.append(this.privateReturnMethod("privateReturnMethodArg"));

		return sb.toString();
	}

	public void publicVoidMethod(String arg) {
		this.wrap("publicVoidMethod! [arg=" + arg + "]");
	}

	public String publicReturnMethod(String arg) {
		return "publicReturnMethod! [arg=" + arg + "]";
	}

	public void wrap(String src) {
		// NOOP
	}

	private void privateVoidMethod(String arg) {
		this.wrap("privateVoidMethod! [arg=" + arg + "]");
	}

	private String privateReturnMethod(String arg) {
		return "privateReturnMethod! [arg=" + arg + "]";
	}
}
