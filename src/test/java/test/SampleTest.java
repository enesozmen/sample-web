package test;

import base.TestBase;
import base.TestListener;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;

public class SampleTest extends TestBase {

	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void test() {
		new HomePage(driver)
				.goUrl("https://www.trendyol.com")
				.searchProduct("pantalon").clickLogin();
	}

//	@Severity(SeverityLevel.CRITICAL)
//	@Test
//	public void test2() {
//		new HomePage(driver)
//				.goUrl("https://www.trendyol.com").searchProduct("pantalon");
//	}

}
