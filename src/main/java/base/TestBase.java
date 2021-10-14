package base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class TestBase {
	static final Logger log = Logger.getLogger(TestBase.class);
	public static WebDriver driver;

	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void setup(@Optional("CHROME") String browser) {
		driver = DriverFactory.getDriver(browser);
	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {
		driver.quit();
	}
}
