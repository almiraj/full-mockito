package pkg.statics;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.mockito.exceptions.base.MockitoException;

public class SystemOrThreadTestTargetTest {

	@Spy
	private SystemOrThreadTestTarget target;

	// Cannot mock java.lang.System
	@Test
	public void test_forSystem() {
		try {
			try (MockedStatic<System> mocked = mockStatic(System.class)) {
				mocked.when(System::currentTimeMillis).thenReturn(999);

				this.target.forSystem();

				fail();
			}
		} catch (MockitoException e) {
			// Unable test java.lang.System
			assertTrue(e.getMessage().contains("java.lang.System"));
		}
	}

	// Cannot mock java.lang.Thread
	@Test
	public void test_forThread() {
		try {
			try (MockedStatic<Thread> mocked = mockStatic(Thread.class)) {
				try {
					mocked.when(() -> Thread.sleep(any()));

					this.target.forThread();

					fail();
				} catch (InterruptedException e) {
				}
			}
		} catch (MockitoException e) {
			// Unable test java.lang.Thread
			assertTrue(e.getMessage().contains("java.lang.Thread"));
		}

	}

}
