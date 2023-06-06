package pkg.statics;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mockStatic;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class StaticStatefulTargetTest {

	@Spy
	private StaticStatefulTarget target;

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
	public void testStaticStatefulClass() {
		try (MockedStatic<StaticStatefulClass> mocked = mockStatic(StaticStatefulClass.class)) {
			mocked.when(StaticStatefulClass::increment).thenReturn(999);

			int actual = this.target.forStatic();

			// Not singleton-pattern cannot be verified times and return value
			assertEquals(999, actual);
		}
	}

	@Test
	public void testSingletonStatefulClass() {
		try (MockedStatic<SingletonStatefulClass> mocked = mockStatic(SingletonStatefulClass.class)) {
			List<Integer> veryfyTimes = new ArrayList<>();

			mocked.when(SingletonStatefulClass::getInstance).thenReturn(new SingletonStatefulClass() {
				@Override
				public int increment() {
					int actual = super.increment();
					veryfyTimes.add(actual);
					return 999;
				}
			});

			int actual = this.target.forSingleton();

			assertEquals(999, actual);
			// Singleton-pattern can be verified as follows
			assertEquals(1, veryfyTimes.size());
			assertEquals(101, (int) veryfyTimes.get(0));
		}
	}

}
