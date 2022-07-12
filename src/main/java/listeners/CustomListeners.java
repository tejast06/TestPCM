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
        System.out.println("Test start");

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(result.getTestName());

    }

    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed");
        try {
            getFailedScreenShot(result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

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
