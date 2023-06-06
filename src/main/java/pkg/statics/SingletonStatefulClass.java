package pkg.statics;

import java.util.concurrent.atomic.AtomicInteger;

public class SingletonStatefulClass {

	private static final SingletonStatefulClass INSTANCE = new SingletonStatefulClass();

	private final AtomicInteger counter = new AtomicInteger(100);

	public static SingletonStatefulClass getInstance() {
		return INSTANCE;
	}

	public int increment() {
		return this.counter.addAndGet(1);
	}

}
