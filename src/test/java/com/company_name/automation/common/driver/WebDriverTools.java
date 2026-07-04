package com.company_name.automation.common.driver;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class WebDriverTools {
    
    private final WebDriver driver;
    
    private final SearchContext searchContext;
    
    public WebDriverTools(WebDriver driver) {
        this.driver = driver;
        this.searchContext = driver;
    }
    
    public WebDriverTools(WebDriver driver, SearchContext searchContext) {
        this.driver = driver;
        this.searchContext = (searchContext != null) ? searchContext : driver;
    }
    
    public boolean isElementVisible(WebElement element)
    {
        try{
            return element.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
    public Boolean clickableClick(final WebElement ele)
    {
        try{
            waitForElementEnabled(ele, 5);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Element is not enabled after waiting for 5 seconds");
        }
        for(int i=0; i<5; i++)
        {
            try{
                ele.click();
                return true;
            }
            catch (Exception e)
            {
                try{
                    Thread.sleep(500);
                }
                catch (InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return false;
    }

    // Refresh Page
    public void refreshPage() {
        driver.navigate().refresh();
    }

    // Get Current URL
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    // Wait for URL contains
    public boolean waitForUrlContains(String urlPortion, int timeoutInSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.urlContains(urlPortion));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Wait for page load
    public boolean waitForPageLoad(int timeoutInSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(webDriver -> ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState").equals("complete"));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Is Element Present - By version
    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Is Element Present - WebElement version
    public boolean isElementPresent(WebElement element) {
        try {
            return element != null && element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Wait for Element Visible - By version
    public boolean waitForElementVisible(By by, int timeoutInSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Wait for Element Visible - WebElement version
    public boolean waitForElementVisible(WebElement element, int timeoutInSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Wait for Element Enabled - By version
    public boolean waitForElementEnabled(By by, int timeoutInSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.elementToBeClickable(by));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Wait for Element Enabled - WebElement version
    public boolean waitForElementEnabled(WebElement element, int timeoutInSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Wait for Element to Contain Text - By version
    public boolean waitForElementToContainText(By by, String text, int timeoutInSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.textToBePresentInElementLocated(by, text));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Wait for Element to Contain Text - WebElement version
    public boolean waitForElementToContainText(WebElement element, String text, int timeoutInSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.textToBePresentInElement(element, text));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Wait for Element Invisible - By version
    public boolean waitForElementInvisible(By by, int timeoutInSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.invisibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Wait for Element Invisible - WebElement version
    public boolean waitForElementInvisible(WebElement element, int timeoutInSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.invisibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
