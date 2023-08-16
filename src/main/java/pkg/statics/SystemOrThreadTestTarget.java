package pkg.statics;

public class SystemOrThreadTestTarget {

	public long forSystem() {
		return System.currentTimeMillis();
	}

	public void forThread() throws InterruptedException {
		Thread.sleep(0);
	}

}
