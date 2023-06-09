package pkg.multiplecalled;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MultipleCalledTargetMockTest {

	@Mock
	private MultipleCalledMockTargetBean bean;

	@InjectMocks
	private MultipleCalledMockTarget target;

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
	public void testNonMock() {
		List<String> list = this.target.mainMethod();

		// Illegal assertion because mock's method is not set
		assertEquals(Arrays.asList(null, null), list);
	}

	@Test
	public void testMultipleCalledMockOnTimes() {
		doReturn("a").doReturn("b").when(this.bean).returnCounter(anyInt());

		List<String> list = this.target.mainMethod();

		assertEquals(Arrays.asList("a", "b"), list);
	}

	@Test
	public void testMultipleCalledMockOnArguments() {
		doReturn("a").when(this.bean).returnCounter(0);
		doReturn("b").when(this.bean).returnCounter(1);

		List<String> list = this.target.mainMethod();

		assertEquals(Arrays.asList("a", "b"), list);
	}

}
