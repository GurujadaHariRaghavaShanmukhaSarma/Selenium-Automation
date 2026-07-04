package com.company_name.automation.project.project_name.pages;

import com.company_name.automation.common.pages.*;
import com.company_name.automation.common.pages.ProjectBasePage;
import com.company_name.automation.project.project_name.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FirstPageFile extends Page<FirstPageFile> {

    ProjectBasePage projectBasePage;

    @FindBy(xpath = "//h1")
    private WebElement pageTitle;

    public FirstPageFile(WebDriver driver) {
        super(driver);
        projectBasePage = new ProjectBasePage(driver);

    }

    protected void load(){
        projectBasePage.url(Constants.project_details.application_url).get();
    }

    @Override
    protected void isLoaded() throws Error {
        isLoaded.forThis(this.driver).whenElementIsPresent(pageTitle, "Unique element on the first page");
    }

    public void waitForPageToLoad(int timeoutInSeconds) {
        tools.waitForPageLoad(timeoutInSeconds);
    }

    public boolean isHeaderTitleDisplayed() {
        return tools.isElementVisible(pageTitle);
    }
}

