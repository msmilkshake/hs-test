package outcomes.feedback_on_exception;

import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testcase.TestCase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.lang.ArithmeticException;
import java.util.Arrays;
import java.util.List;

class FeedbackOnExceptionTest1Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        System.out.println(1 / 0);
    }
}

public class FeedbackOnExceptionTest1 extends StageTest {

    public FeedbackOnExceptionTest1() {
        super(FeedbackOnExceptionTest1Main.class);
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void before() {
        exception.expect(AssertionError.class);
        exception.expectMessage("Exception in test #1");
        exception.expectMessage("Do not divide by zero!\n" +
            "\n" +
            "java.lang.ArithmeticException: / by zero");
    }

    @Override
    public List<TestCase> generate() {
        return Arrays.asList(
            new TestCase()
                .feedbackOnException(
                    ArithmeticException.class,
                    "Do not divide by zero!")
        );
    }

    @Override
    public CheckResult check(String reply, Object attach) {
        return CheckResult.correct();
    }
}
