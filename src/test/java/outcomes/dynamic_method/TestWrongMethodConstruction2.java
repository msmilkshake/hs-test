package outcomes.dynamic_method;

import org.hyperskill.hstest.dynamic.input.DynamicTestingMethod;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class TestWrongMethodConstruction2 extends StageTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void before() {
        exception.expect(AssertionError.class);
        exception.expectMessage("Fatal error during testing, " +
            "please send the report to support@hyperskill.org");

        exception.expectMessage("FatalError: " +
            "Method \"test\" should take 0 arguments. Found: 1");
    }

    @DynamicTestingMethod
    CheckResult test(int x) {
        return null;
    }
}
