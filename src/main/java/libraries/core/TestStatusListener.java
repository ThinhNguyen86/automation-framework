package libraries.core;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestStatusListener extends TestListenerAdapter {

    private int count = 1;

    @Override
    public void onTestSuccess(final ITestResult tr) {
        logTestResultToConsole("PASSED", tr);
    }

    @Override
    public void onTestFailure(final ITestResult tr) {
        logTestResultToConsole("FAILED", tr);
    }

    private void logTestResultToConsole(final String testStatus, final ITestResult tr) {
        String log = String.format("%s - Test script #%d: %s\n", testStatus, count++, tr.getMethod().getMethodName());
        System.out.println(log);
    }

}
