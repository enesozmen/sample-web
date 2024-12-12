package base;

import io.qameta.allure.Allure;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class TestListener extends TestBase implements ITestListener {
    final static Logger logger = Logger.getLogger(TestListener.class);

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.info("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");

        WebDriver driver = TestBase.driver;

        if (driver != null) {
            // Ekran görüntüsü al ve Allure raporuna ekle
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot on Failure", "image/png", new ByteArrayInputStream(screenshotBytes), ".png");

            logger.info("Screenshot captured for test case: " + getTestMethodName(iTestResult));
        }

        // Hata mesajını Allure raporuna ekle
        Throwable throwable = iTestResult.getThrowable();
        if (throwable != null) {
            String errorMessage = throwable.getMessage();
            Allure.addAttachment("Error Message", "text/plain", new ByteArrayInputStream(errorMessage.getBytes(StandardCharsets.UTF_8)), ".txt");
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
