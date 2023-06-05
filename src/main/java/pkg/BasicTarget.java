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
		this.wrapPublicVoidMethod("publicVoidMethod! [args=" + arg + "]");
	}
	protected void wrapPublicVoidMethod(String src) {
		// NOOP
	}

	public String publicReturnMethod(String arg) {
		return "publicReturnMethod! [args=" + arg + "]";
	}

	private void privateVoidMethod(String arg) {
		this.wrapPrivateVoidMethod("privateVoidMethod! [args=" + arg + "]");
	}
	protected void wrapPrivateVoidMethod(String src) {
		// NOOP
	}

	private String privateReturnMethod(String arg) {
		return "privateReturnMethod! [args=" + arg + "]";
	}
}
