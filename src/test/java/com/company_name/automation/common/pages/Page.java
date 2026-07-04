package com.company_name.automation.common.pages;

import com.company_name.automation.common.driver.WebDriverTools;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.SlowLoadableComponent;

import java.time.Clock;

public abstract class Page<T extends Page<T>> extends SlowLoadableComponent {

    protected WebDriver driver;
    public WebDriverTools tools;

    public Page(WebDriver driver) {
        super(Clock.systemDefaultZone(), 10);
        this.driver = driver;
        this.tools = new WebDriverTools(this.driver);
        PageFactory.initElements(driver, this);


    }

    @Override
    protected void load()
    {

    }

}
