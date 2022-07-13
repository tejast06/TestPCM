package listeners;

import base.BaseClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class CustomListeners extends BaseClass implements ITestListener {

    public CustomListeners() throws IOException {
        super();
    }

    @Override
    public void onTestStart(ITestResult result) {

        String testCaseName =result.getMethod().getMethodName();

        System.out.println("Test start" + testCaseName);

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testCaseName =result.getMethod().getMethodName();

        System.out.println("Test success" + testCaseName);

    }

    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed");
        try {
            getFailedScreenShot(result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String testCaseName =result.getMethod().getMethodName();

        System.out.println("Test failed" + testCaseName);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testCaseName =result.getMethod().getMethodName();

        System.out.println("Test skipped" + testCaseName);

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }


}
