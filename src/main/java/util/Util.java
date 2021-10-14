package util;

import java.util.List;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {

    public WebDriver driver;
    public WebDriverWait wait;
    final static Logger logger = Logger.getLogger(Util.class);

    public Util(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    @Step("Find Element")
    protected WebElement findElement(By by) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        logger.info("Found element : " + element.toString());
        return element;
    }

    @Step("Wait until clickable")
    protected WebElement waitUntilClickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    @Step("Find Elements")
    protected List<WebElement> findElements(By by) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    @Step("Click Element")
    protected void clickElement(By by) {
        waitUntilClickable(by);
        WebElement element = findElement(by);
        element.click();
        logger.info("Clicked element : " + element.toString());
    }

    @Step("Click Element")
    protected void clickElement(WebElement element) {
        element.click();
        logger.info("Clicked element : " + element.toString());
    }

    @Step("Set Text")
    protected void sendKeys(By by, String text) {
        waitUntilClickable(by);
        WebElement element = findElement(by);
        element.clear();
        logger.info("Element Send Keys : " + text + "-" + element);
        element.sendKeys(text);
    }

    @Step("Get text")
    protected String getText(By by) {
        return findElement(by).getText();
    }

    @Step("Get attribute")
    protected String getAttribute(By by, String attr) {
        return findElement(by).getAttribute(attr);
    }

    @Step("Hover element")
    protected void hover(By by) {
        Actions action = new Actions(driver);
        WebElement element = findElement(by);
        action.moveToElement(element).perform();
    }

    @Step("Click element with text")
    protected void clickElementWithText(By by, String value) {
        List<WebElement> elements = findElements(by);
        elements.stream().filter(x -> value.equals(x.getText())).findFirst()
                .orElseThrow(() -> new IllegalStateException("Element with text: " + value + " is not found!")).click();
    }

    @Step("Click element with javascript")
    protected void jsClicker(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }
}
