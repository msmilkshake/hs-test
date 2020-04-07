package org.hyperskill.hstest.v7.outcomes;

import org.hyperskill.hstest.v7.dynamic.output.SystemOutHandler;
import org.hyperskill.hstest.v7.exception.outcomes.ErrorWithFeedback;
import org.hyperskill.hstest.v7.exception.outcomes.ExceptionWithFeedback;
import org.hyperskill.hstest.v7.exception.testing.TimeLimitException;
import org.hyperskill.hstest.v7.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.v7.stage.StageTest;

import java.nio.file.FileSystemException;

public abstract class Outcome {

    int testNumber = 0;
    String errorText = "";
    String stackTrace = "";

    protected abstract String getType();

    protected String getTypeSuffix() {
        return "";
    }

    @Override
    public final String toString() {

        String whenErrorHappened;
        if (testNumber == 0) {
            whenErrorHappened = " during testing";
        } else {
            whenErrorHappened = " in test #" + testNumber;
        }

        String result =
            getType() + whenErrorHappened + getTypeSuffix();

        if (!errorText.isEmpty()) {
            result += "\n\n" + errorText.trim();
        }

        if (!stackTrace.isEmpty()) {
            result += "\n\n" + stackTrace.trim();
        }

        String fullLog = SystemOutHandler.getDynamicOutput();

        if (fullLog.trim().length() != 0) {
            result += "\n\n";
            result += "Please find below the output of your program during this failed test.\n";
            if (StageTest.getCurrTestRun().isInputUsed()) {
                result += "Note that the '>' character indicates the beginning of the input line.\n";
            }
            result += "\n---\n\n";
            result += fullLog;
        }

        return result.trim();
    }

    public static Outcome getOutcome(Throwable t, int currTest) {
        if (t instanceof WrongAnswer) {
            return new WrongAnswerOutcome(currTest,
                ((WrongAnswer) t).getFeedbackText().trim());

        } else if (t instanceof ExceptionWithFeedback) {
            ExceptionWithFeedback ex = (ExceptionWithFeedback) t;
            Throwable realUserException = ex.getRealException();
            String errorText = ex.getErrorText();
            return new ExceptionOutcome(currTest, realUserException, errorText);

        } else if (t instanceof ErrorWithFeedback
            || t instanceof FileSystemException
            || t instanceof TimeLimitException) {

            return new ErrorOutcome(currTest, t);

        } else {
            return new FatalErrorOutcome(currTest, t);
        }
    }
}
