package base;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;


public class DriverFactory {

    public static WebDriver getDriver(String browser) {

        switch (browser) {
            case "IE":
                WebDriverManager.iedriver().setup();
                return new InternetExplorerDriver();
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "CHROME":
                WebDriver driver;
                //WebDriverManager.chromedriver().setup();
                String os = System.getProperty("os.name").toLowerCase();
                if (os.contains("win")) {
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                } else if (os.contains("osx") || os.contains("os x")) {
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
                } else if (os.contains("nix") || os.contains("aix") || os.contains("nux")) {
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriverlinux");
                }

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-extensions");
                driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                return driver;
            default:

//	    	WebDriverManager.chromedriver().setup();
//	    	If it is not match with your Chrome version please try below
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
                options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-extensions");
                driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                return driver;
        }
    }

}


