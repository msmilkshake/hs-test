package outcomes;

import org.hyperskill.hstest.v7.stage.BaseStageTest;
import org.hyperskill.hstest.v7.testcase.CheckResult;
import org.hyperskill.hstest.v7.testcase.TestCase;
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

public class FatalErrorEmptyTestCases extends BaseStageTest {

    public FatalErrorEmptyTestCases() {
        super(FatalErrorEmptyTestCasesMain.class);
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void before() {
        exception.expect(AssertionError.class);
        exception.expectMessage("Fatal error during testing, please send the report to support@hyperskill.org");
        exception.expectMessage("No tests provided by \"generate\" method");
    }

    @Override
    public List<TestCase> generate() {
        return Arrays.asList();
    }

    @Override
    public CheckResult check(String reply, Object attach) {
        return CheckResult.TRUE;
    }

}
