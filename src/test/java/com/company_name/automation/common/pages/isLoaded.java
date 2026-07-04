package com.company_name.automation.common.pages;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class isLoaded {

    private final SearchContext searchContext;

    private final WebDriver driver;

    public isLoaded(SearchContext element, WebDriver driver) {
        this.driver = driver;
        if(element!=null)
        {
            this.searchContext = element;
        }
        else{
            this.searchContext = driver;
        }
    }

    public static isLoaded forThis(WebDriver driver)
    {
        return new isLoaded(driver,null);
    }

    public isLoaded whenElementIsPresent(WebElement usingWebElement, String description)
    {
        try{
            usingWebElement.getLocation();
        }
        catch (WebDriverException e)
        {
            throw new AssertionError("Expected element '" + description + "' to be present, but it was not found.");
        }
        return this;
    }
}
