import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class PlaceHolderTest {

	@Test
	public void should_always_return_foo() {
		assertThat(new PlaceHolder().getFoo()).isEqualTo("foo");
	}
}
