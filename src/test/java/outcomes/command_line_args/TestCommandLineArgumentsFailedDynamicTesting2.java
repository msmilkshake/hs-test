package outcomes.command_line_args;

import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testcase.TestCase;
import org.hyperskill.hstest.testing.TestedProgram;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;

public class TestCommandLineArgumentsFailedDynamicTesting2 extends StageTest<String> {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void before() {
        exception.expect(AssertionError.class);
        exception.expectMessage(
            "Wrong answer in test #1\n" +
                "\n" +
                "Arguments: -in 123 -out 234"
        );

        exception.expectMessage(not(containsString("Fatal error")));
    }

    @Override
    public List<TestCase<String>> generate() {
        return Collections.singletonList(
            new TestCase<String>().setDynamicTesting(() -> {
                TestedProgram pr = new TestedProgram(Main2.class);
                pr.start("-in", "123", "-out", "234");
                return new CheckResult(false, "");
            })
        );
    }
}
