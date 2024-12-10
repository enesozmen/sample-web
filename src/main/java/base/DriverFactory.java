package base;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverFactory {

    public static WebDriver getDriver(String browser) {

        switch (browser) {
            case "FIREFOX":
                return new FirefoxDriver();
            case "CHROME":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--kiosk");
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-extensions");
                options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
                return new ChromeDriver(options);
            default:

                return new ChromeDriver();
        }
    }

}
