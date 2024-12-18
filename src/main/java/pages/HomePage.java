package pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.Util;

public class HomePage extends Util {
	final static Logger logger = Logger.getLogger(HomePage.class);

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public HomePage goUrl(String url) {
		driver.get(url);
		logger.info("Opening website");
		return this;
	}

	@Step("Click Login")
	public HomePage clickLogin(){
		clickElementIfExist(By.className("modal-close"));
		return this;
	}

	@Step("Search product")
	public HomePage searchProduct(String product){
		sendKeys(By.xpath("//input[@class='seardsvfdsfvdsfdch-box']"),product);
		return this;
	}
}
