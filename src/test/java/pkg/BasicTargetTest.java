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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class BasicTargetTest {

	@Spy
	@InjectMocks
	private BasicTarget basicTarget;

	@Mock
	private InjectedObject injectedObject;

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
	public void testMainMethod_nonSpy() {
		doReturn("SOMETHING").when(this.injectedObject).getSomething();
		String actual = this.basicTarget.mainMethod();

		String expected =
				"publicReturnMethod! [args=publicReturnMethodArg!]\n"
				+ "privateReturnMethod! [args=privateReturnMethodArg!]\n"
				+ "injectedObject return: SOMETHING";

		assertEquals(expected, actual);
	}

	@Test
	public void testMainMethod_mocked() {
		doReturn("SOMETHING").when(this.injectedObject).getSomething();

		String actual = this.basicTarget.mainMethod();

		String expected =
				"publicReturnMethod! [args=publicReturnMethodArg!]\n"
				+ "privateReturnMethod! [args=privateReturnMethodArg!]\n"
				+ "injectedObject return: SOMETHING";

		assertEquals(expected, actual);
		verify(this.basicTarget, times(1)).wrapPublicVoidMethod(any());
		verify(this.basicTarget, times(1)).wrapPrivateVoidMethod(any());
	}

	@Test
	public void testMainMethod_publicVoidMethod() {
		doReturn("SOMETHING").when(this.injectedObject).getSomething();
		doNothing().when(this.basicTarget).publicVoidMethod(any());

		String actual = this.basicTarget.mainMethod();

		String expected =
				"publicReturnMethod! [args=publicReturnMethodArg!]\n"
				+ "privateReturnMethod! [args=privateReturnMethodArg!]\n"
				+ "injectedObject return: SOMETHING";

		assertEquals(expected, actual);
		verify(this.basicTarget, times(0)).wrapPublicVoidMethod(any());
		verify(this.basicTarget, times(1)).wrapPrivateVoidMethod(any());
	}

	@Test
	public void testMainMethod_publicReturnMethod() {
		doReturn("SOMETHING").when(this.injectedObject).getSomething();
		doReturn("aaa").when(this.basicTarget).publicReturnMethod(any());

		String actual = this.basicTarget.mainMethod();

		String expected =
				"aaa\n"
				+ "privateReturnMethod! [args=privateReturnMethodArg!]\n"
				+ "injectedObject return: SOMETHING";

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
