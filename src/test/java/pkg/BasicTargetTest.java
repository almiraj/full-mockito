package pkg;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

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
	}

	@Test
	public void testMainMethod_mocked() {
		doReturn("publicReturnMethod? [---]").when(this.basicTarget).publicReturnMethod(any());

		String actual = this.basicTarget.mainMethod();

		String expected =
				"publicReturnMethod? [---]\n"
				+ "privateReturnMethod! [args=privateReturnMethodArg!]\n";

		assertEquals(expected, actual);
	}

}
