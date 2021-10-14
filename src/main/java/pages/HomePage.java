package pages;

import base.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
	final static Logger logger = Logger.getLogger(HomePage.class);

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public HomePage goUrl(String url) {
		driver.get(url);
		logger.info("Opening website");
		return this;
	}

	public HomePage clickLogin(){
		clickElement(By.className("dd"));
		return this;
	}

	public HomePage searchProduct(String product){
		sendKeys(By.xpath("//input[@class='search-box']"),product);
		return this;
	}
}
