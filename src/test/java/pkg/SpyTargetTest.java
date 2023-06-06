package pkg;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class SpyTargetTest {

	@Spy
	private SpyTarget target;

	private AutoCloseable mock;

	@Before
	public void setUp() {
		this.mock = MockitoAnnotations.openMocks(this);
	}

	@After
	public void tearDown() throws Exception {
		this.mock.close();
	}

	@Test
	public void testNonSpy() {
		String actual = this.target.mainMethod();

		assertEquals("b[argB]", actual);
		verify(this.target, times(1)).wrap("a[argA]");
	}

	@Test
	public void testSpied() {
		doNothing().when(this.target).voidMethod(any());

		String actual = this.target.mainMethod();

		assertEquals("b[argB]", actual);
		// is not called when spied
		verify(this.target, times(0)).wrap("a[argA]");
	}

}
