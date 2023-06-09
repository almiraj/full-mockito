package pkg.multiplecalled;

import java.util.ArrayList;
import java.util.List;

public class MultipleCalledMockTarget {

	private MultipleCalledMockTargetBean bean;

	public List<String> mainMethod() {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			list.add(this.bean.returnCounter(i));
		}
		return list;
	}

}
