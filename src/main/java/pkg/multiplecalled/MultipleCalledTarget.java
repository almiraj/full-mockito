package pkg.multiplecalled;

import java.util.ArrayList;
import java.util.List;

public class MultipleCalledTarget {

	public List<String> mainMethod() {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			list.add(this.returnCounter(i));
		}
		return list;
	}

	public String returnCounter(int i) {
		return String.valueOf(i);
	}

}
