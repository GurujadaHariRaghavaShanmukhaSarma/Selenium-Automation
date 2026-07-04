package com.company_name.automation.project.project_name.steps;

import com.company_name.automation.common.steps.Steps;
import com.company_name.automation.common.steps.World;
import com.company_name.automation.project.project_name.pages.FirstPageFile;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class FirstStepFile extends Steps {

    private FirstPageFile firstPageFile;

    public FirstStepFile(World world) {
        super(world);
        this.firstPageFile = new FirstPageFile(world.driver);
    }

    @Given("User opens the application")
    public void userOpensTheApplication() {
        firstPageFile.get();
    }

    @When("User waits for page to load")
    public void userWaitsForPageToLoad() {
        firstPageFile.waitForPageToLoad(10);
    }

    @And("User validates that the page header title is not empty")
    public void userValidatesThatPageHeaderTitleIsNotEmpty() {
        Assert.assertFalse("Page header title is not displayed", firstPageFile.isHeaderTitleDisplayed());
        world.report.captureScreenshot(world.driver, "Page header title is displayed");
        world.report.log("Page header title is displayed");
    }
}
