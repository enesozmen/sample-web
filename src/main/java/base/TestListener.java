package base;

import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends TestBase implements ITestListener {
    final static Logger logger = Logger.getLogger(TestListener.class);

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Error Message", type = "text/plain")
    public String saveErrorMessage(String message) {
        return message;
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.info("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");

        WebDriver driver = TestBase.driver;

        if (driver != null) {
            logger.info("Screenshot captured for test case: " + getTestMethodName(iTestResult));
            saveScreenshotPNG(driver);
        }

        Throwable throwable = iTestResult.getThrowable();
        if (throwable != null) {
            saveErrorMessage(throwable.getMessage());
        }

        logger.info(getTestMethodName(iTestResult) + " failed and additional info logged to Allure!");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger.info("Test " + getTestMethodName(iTestResult) + " succeeded.");
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        logger.info("Starting test: " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.info("Test " + getTestMethodName(iTestResult) + " skipped.");
    }
}