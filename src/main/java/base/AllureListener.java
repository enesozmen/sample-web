package base;

import io.qameta.allure.Allure;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class AllureListener implements TestLifecycleListener {

    @Override
    public void beforeTestStop(TestResult result) {
        if (result.getStatus() == Status.FAILED || result.getStatus() == Status.BROKEN) {
            WebDriver currentDriver = TestBase.driver;
            if (currentDriver != null) {
                Allure.addAttachment(
                        "Screenshot",
                        new ByteArrayInputStream(((TakesScreenshot) currentDriver).getScreenshotAs(OutputType.BYTES))
                );
            }
        }
    }
}
