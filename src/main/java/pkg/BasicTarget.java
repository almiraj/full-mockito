package pkg;

public class BasicTarget {

	@SuppressWarnings("unused")
	private static final String PRIVATE_STATIC_FINAL_FIELD_VALUE = "PRIVATE_STATIC_FINAL_FIELD_VALUE!";

	@SuppressWarnings("unused")
	private static String PRIVATE_STATIC_FIELD_VALUE = "PRIVATE_STATIC_FIELD_VALUE!";

	@SuppressWarnings("unused")
	private String privateFieldValue = "privateFieldValue!";

	public String mainMethod() {
		StringBuilder sb = new StringBuilder();

		this.publicVoidMethod("publicVoidMethodArg!");
		sb.append(this.publicReturnMethod("publicReturnMethodArg!"));
		sb.append("\n");

		this.privateVoidMethod("privateVoidMethodArg!");
		sb.append(this.privateReturnMethod("privateReturnMethodArg!"));
		sb.append("\n");

		return sb.toString();
	}

	public void publicVoidMethod(String arg) {
		this.wrap("publicVoidMethod! [args=" + arg + "]");
	}

	public String publicReturnMethod(String arg) {
		return "publicReturnMethod! [args=" + arg + "]";
	}

	public void wrap(String src) {
		// NOOP
	}

	private void privateVoidMethod(String arg) {
		this.wrap("privateVoidMethod! [args=" + arg + "]");
	}

	private String privateReturnMethod(String arg) {
		return "privateReturnMethod! [args=" + arg + "]";
	}
}
