package pkg;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MockTargetTest {

	@Mock
	private MockTargetBean bean;

	@InjectMocks
	private MockTarget target;

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
		String actual = this.target.mainMethod();

		// is not "something"
		assertEquals(null, actual);
		verify(this.bean, times(1)).getSomething();
	}

	@Test
	public void testMocked() {
		doReturn("SOMETHING").when(this.bean).getSomething();

		String actual = this.target.mainMethod();

		assertEquals("SOMETHING", actual);
		verify(this.bean, times(1)).getSomething();
	}

}
