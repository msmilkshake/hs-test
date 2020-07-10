package outcomes.fatal_error;

import org.hyperskill.hstest.stage.StageTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class FatalErrorTestReport extends StageTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void before() {
        exception.expect(AssertionError.class);
        exception.expectMessage(
            "Fatal error during testing, please send the report to support@hyperskill.org\n"
                + "\n"
                + "Submitted via IDE\n"
                + "\n"
                + "OS ");
    }
}
