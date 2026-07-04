package com.company_name.automation.common.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import static org.hamcrest.MatcherAssert.assertThat;

public class ProjectBasePage extends Page<ProjectBasePage> {
    
    String url;
    String expected;
    
    public ProjectBasePage(WebDriver driver) {
        
        super(driver);
    }
    
    @Override
    protected void load() {
        if(expected == null)
        {
            expected = url;
        }
        if(url == null) {
            throw new RuntimeException("URL cannot be null");
        }
        driver.get(url);
    }

    @Override
    protected void isLoaded() throws Error {
        if(expected == null){
            expected = url;
        }
        Assert.assertTrue(driver.getCurrentUrl().contains(expected));
//        String currentUrl = driver.getCurrentUrl();
//        if(!currentUrl.equals(expected)) {
//            throw new RuntimeException("Current URL: " + currentUrl + " does not match expected URL: " + expected);
//        }
    }
    
    public ProjectBasePage url(String url)
    {

        this.url=url;
        return this;
    }
    
    public void microSoftAuthHandler() {
        try {
            // Store the parent window handle
            String parentWindow = driver.getWindowHandle();
            
            // Get all window handles
            java.util.Set<String> allWindows = driver.getWindowHandles();
            
            // Switch to the Microsoft login window if it exists
            for(String window : allWindows) {
                if(!window.equals(parentWindow)) {
                    driver.switchTo().window(window);
                    break;
                }
            }
            
            // Check if we're on Microsoft login page
            String currentUrl = driver.getCurrentUrl();
            if(currentUrl.contains("login.microsoftonline.com") || 
               currentUrl.contains("login.microsoft.com")) {
                
                // Wait for page to load
                tools.waitForPageLoad(10);
                
                // Close the Microsoft auth window and switch back to parent
                driver.close();
                driver.switchTo().window(parentWindow);
            }
        } catch(WebDriverException e) {
            // If window switching fails, just ensure we're on the parent window
            try {
                String parentWindow = driver.getWindowHandle();
                driver.switchTo().window(parentWindow);
            } catch(Exception ex) {
                // Log exception if needed
                System.out.println("Error handling Microsoft Auth: " + ex.getMessage());
            }
        }
    }
}
