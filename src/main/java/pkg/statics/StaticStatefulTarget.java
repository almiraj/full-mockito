package pkg.statics;

public class StaticStatefulTarget {

	public int forStatic() {
		return StaticStatefulClass.increment();
	}

	public int forSingleton() {
		return SingletonStatefulClass.getInstance().increment();
	}

}
