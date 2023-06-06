package pkg;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class SpyAndMockTargetTest {

	@Mock
	private MockTargetBean bean;

	@Spy
	@InjectMocks
	private SpyAndMockTarget target;

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
	public void testNonSpyOrMock() {
		String actual = this.target.mainMethod();

		assertEquals("b[argB]", actual);
	}

	@Test
	public void testSpiedAndMocked() {
		doReturn("SOMETHING").when(this.bean).getSomething();
		doReturn("c").when(this.target).returnMethod(any());

		String actual = this.target.mainMethod();

		assertEquals("c", actual);
		verify(this.target, times(1)).voidMethod("SOMETHING");
	}

	// easy to read difference between expected and actual with stack-trace when AssertionError
	@Test
	public void testSpied_voidMethod_argumentCaptorStyle() {
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		doReturn("SOMETHING").when(this.bean).getSomething();
		doReturn("c").when(this.target).returnMethod(any());

		String actual = this.target.mainMethod();

		assertEquals("c", actual);
		verify(this.target, times(1)).voidMethod(captor.capture());
		assertEquals("SOMETHING", captor.getValue());
	}

}
