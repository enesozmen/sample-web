package base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class TestBase {
	static final Logger logger = Logger.getLogger(TestBase.class);
	public static WebDriver driver;

	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void setup(@Optional("CHROME") String browser) {
		driver = DriverFactory.getDriver("CHROME");
		logger.info("****** TEST STARTED ******");
	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {
		driver.quit();
		logger.info("****** TEST FINISHED ******");
	}
}
