package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class TestBase {
	public static WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	public void initializeDriver() {
		driver = DriverFactory.getDriver("CHROME");
		//driver.navigate().to("https://qa-backoffice.ozansuperapp.com/auth/login");
	}

	@AfterMethod(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}
}
