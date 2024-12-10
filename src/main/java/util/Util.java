package util;

import java.time.Duration;
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

    public Util(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected WebElement findElement(By by) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return element;
    }

    protected List<WebElement> findElements(By by) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    protected WebElement waitUntilClickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    protected WebElement waitForElement(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected WebElement waitForElement(By by, int waitSeconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds));
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected void clickElement(By by) {
        waitUntilClickable(by);
        WebElement element = findElement(by);
        element.click();
    }

    protected void clickElement(WebElement element) {
        element.click();
    }

    protected void clickElementWithText(By by, String value) {
        List<WebElement> elements = findElements(by);
        elements.stream().filter(x -> value.equals(x.getText())).findFirst()
                .orElseThrow(() -> new IllegalStateException("Element with text: " + value + " is not found!")).click();
    }

    protected void jsClicker(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void clickElementIfExist(By element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        if (isElementPresent(element)) {
            wait.until(ExpectedConditions.presenceOfElementLocated(element)).click();
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void clickElementIfExist(By element, int waitSeconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds));
        if (isElementPresent(element)) {
            wait.until(ExpectedConditions.presenceOfElementLocated(element)).click();
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    protected void sendKeys(By by, String text) {
        try {
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(by))
                    .sendKeys(text);
        } catch (Exception ex) {
            wait.ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(by))
                    .sendKeys(text);
        }
    }

    public void sendKeysAtIndex(By element, String text, int index) {
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(element)).get(index).sendKeys(text);
    }

    protected String getText(By by) {
        return findElement(by).getText();
    }

    public String getTextWithIndex(By element, int index) {
        return findElements(element).get(index).getText();
    }

    protected String getAttribute(By by, String attr) {
        return findElement(by).getAttribute(attr);
    }

    protected void hover(By by) {
        Actions action = new Actions(driver);
        WebElement element = findElement(by);
        action.moveToElement(element).perform();
    }

    public boolean isElementPresent(By element) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(element));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean isElementPresent(By element, int waitSeconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(element));
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            return true;
        } catch (Exception e) {
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            return false;
        }

    }

}
