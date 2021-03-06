package outcomes.fatal_error;

import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testcase.TestCase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

class FatalErrorEmptyTestCasesMain {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}

public class FatalErrorEmptyTestCases extends StageTest {

    public FatalErrorEmptyTestCases() {
        super(FatalErrorEmptyTestCasesMain.class);
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void before() {
        exception.expect(AssertionError.class);
        exception.expectMessage("Fatal error during testing, please send the report to support@hyperskill.org");
        exception.expectMessage("No tests found");
    }

    @Override
    public List<TestCase> generate() {
        return Arrays.asList();
    }

    @Override
    public CheckResult check(String reply, Object attach) {
        return CheckResult.correct();
    }

}
