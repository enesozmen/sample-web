package util;

import java.util.List;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
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

    @Step("Find Elements")
    protected List<WebElement> findElements(By by) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    @Step("Wait until clickable")
    protected WebElement waitUntilClickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
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

    @Step("Click element if exist")
    public void clickElementIfExist(By element) {
        wait = new WebDriverWait(driver, 10);
        if (isElementPresent(element)) {
            wait.until(ExpectedConditions.presenceOfElementLocated(element)).click();
        }
        wait = new WebDriverWait(driver, 30);
    }

    @Step("Click element if exist")
    public void clickElementIfExist(By element, int waitSeconds) {
        wait = new WebDriverWait(driver, waitSeconds);
        if (isElementPresent(element)) {
            wait.until(ExpectedConditions.presenceOfElementLocated(element)).click();
        }
        wait = new WebDriverWait(driver, 30);
    }

    @Step("Set Text")
    protected void sendKeys(By by, String text) {
        try {
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(by))
                    .sendKeys(text);
            logger.info("Set " + text + " to " + by);
        } catch (Exception ex) {
            wait.ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(by))
                    .sendKeys(text);
            logger.info("Set " + text + " to " + by);
        }
    }

    @Step("Set Text at specific index")
    public void sendKeysAtIndex(By element, String text, int index) {
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(element)).get(index).sendKeys(text);
        logger.info("Set " + text + " to " + element + " at index: " + index);
    }

    @Step("Get text")
    protected String getText(By by) {
        return findElement(by).getText();
    }

    @Step("Get text at specific index")
    public String getTextWithIndex(By element, int index) {
        return findElements(element).get(index).getText();
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

    @Step("Is element present or not")
    public boolean isElementPresent(By element) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(element));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Step("Is element present or not")
    public boolean isElementPresent(By element, int waitSeconds) {
        wait = new WebDriverWait(driver, waitSeconds);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(element));
            wait = new WebDriverWait(driver, 30);
            return true;
        } catch (Exception e) {
            wait = new WebDriverWait(driver, 30);
            return false;
        }

    }

}
