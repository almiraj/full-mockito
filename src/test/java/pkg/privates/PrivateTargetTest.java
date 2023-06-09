package pkg.privates;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

// Not related to Mockito
public class PrivateTargetTest {

	@Test
	public void testPrivateField() throws ReflectiveOperationException {
		PrivateTarget target = new PrivateTarget();

		Field pfField = PrivateTarget.class.getDeclaredField("privateField");
		pfField.setAccessible(true);
		pfField.set(target, "refValue");

		Field psfField = PrivateTarget.class.getDeclaredField("privateStaticField");
		psfField.setAccessible(true);
		psfField.set(PrivateTarget.class, "stRefValue");

		String actual = target.mainMethod();

		assertEquals(actual, "stRefValue_refValue");
	}

	@Test
	public void testPrivateStaticMethod() throws ReflectiveOperationException {
		PrivateTarget target = new PrivateTarget();

		Method m = PrivateTarget.class.getDeclaredMethod("privateStaticMethod", String.class);
		m.setAccessible(true);
		String actual = (String) m.invoke(target, "ARGS");

		assertEquals(actual, "[psm:ARGS]");
	}

	@Test
	public void testPrivateMethod() throws ReflectiveOperationException {
		PrivateTarget target = new PrivateTarget();

		Method m = PrivateTarget.class.getDeclaredMethod("privateMethod", String.class);
		m.setAccessible(true);
		String actual = (String) m.invoke(target, "ARGS");

		assertEquals(actual, "[pm:ARGS]");
	}

}
