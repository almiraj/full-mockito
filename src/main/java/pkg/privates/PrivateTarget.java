package pkg.privates;

@SuppressWarnings("unused")
public class PrivateTarget {

	private static String privateStaticField = "psf";

	private String privateField = "pf";

	public String mainMethod() {
		return privateStaticField + "_" + this.privateField;
	}

	private static String privateStaticMethod(String args) {
		return "[psm:" + args + "]";
	}

	private String privateMethod(String args) {
		return "[pm:" + args + "]";
	}

}
