package pkg;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class BasicTargetTest {

	private AutoCloseable mock;

	@Before
	public void setUp() {
		this.mock = MockitoAnnotations.openMocks(this);
	}

	@After
	public void tearDown() throws Exception {
		this.mock.close();
	}

	@Spy
	private BasicTarget basicTarget;

	@Test
	public void testMainMethod_nonMock() {
		String actual = this.basicTarget.mainMethod();

		String expected =
				"publicReturnMethod! [args=publicReturnMethodArg!]\n"
				+ "privateReturnMethod! [args=privateReturnMethodArg!]\n";

		assertEquals(expected, actual);
		verify(this.basicTarget, times(1)).wrapPublicVoidMethod(any());
		verify(this.basicTarget, times(1)).wrapPrivateVoidMethod(any());
	}

	@Test
	public void testMainMethod_publicVoidMethod() {
		doNothing().when(this.basicTarget).publicVoidMethod(any());

		String actual = this.basicTarget.mainMethod();

		String expected =
				"publicReturnMethod! [args=publicReturnMethodArg!]\n"
				+ "privateReturnMethod! [args=privateReturnMethodArg!]\n";

		assertEquals(expected, actual);
		verify(this.basicTarget, times(0)).wrapPublicVoidMethod(any());
		verify(this.basicTarget, times(1)).wrapPrivateVoidMethod(any());
	}

	@Test
	public void testMainMethod_publicReturnMethod() {
		doReturn("aaa").when(this.basicTarget).publicReturnMethod(any());

		String actual = this.basicTarget.mainMethod();

		String expected =
				"aaa\n"
				+ "privateReturnMethod! [args=privateReturnMethodArg!]\n";

		assertEquals(expected, actual);
		verify(this.basicTarget, times(1)).wrapPublicVoidMethod(any());
		verify(this.basicTarget, times(1)).wrapPrivateVoidMethod(any());
	}

	@Test
	public void testMainMethod_privateVoidMethod() {
		// Cannot mock private method
	}

	@Test
	public void testMainMethod_privateReturnMethod() {
		// Cannot mock private method
	}

}
