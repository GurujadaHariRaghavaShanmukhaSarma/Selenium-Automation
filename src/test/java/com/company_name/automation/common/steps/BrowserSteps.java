package com.company_name.automation.common.steps;
import com.company_name.automation.common.driver.WebDriverFactory;
import com.company_name.automation.common.utilities.DataBase;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.company_name.automation.project.project_name.Constants.project_details.*;

public class BrowserSteps extends Steps{
    
    private static boolean webDriverManager = false;

    public BrowserSteps(World world) {
        super(world);
    }

    @Before("@browser")
    public void prologue() {
        webDriverManagerSetup();
        webDriverGet();
    }
    
    @Before("@db")
    public void db_prologue() throws Exception
    {
        if(world.db_instance == null)
        {
            synchronized (DataBase.class)
            {
                    if(world.db_instance == null)
                    {
                        world.db_instance = new DataBase();
                        String environment = System.getProperty("environment");
                        switch (environment)
                        {
                            case "test" :
                                world.db_instance.setConnection("Postgresql",test_postgresql_db_url,test_postgresql_db_user_name,test_postgresql_db_password);
                        }
                    }
            }

        }
    }
    
    @After(value = "@db")
    public void db_epilogue() throws Exception{
        if(world.db_instance != null)
        {
            world.db_instance.close();
        }
    }
    
    @After(value = "@browser", order = 2)
    public void browserAfter1(Scenario scenario)
    {
        epilogue1(scenario);
    }

    protected void epilogue1(Scenario scenario)
    {
        if(scenario.isFailed())
        {
            byte[] screenshot = ((TakesScreenshot) world.driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot");
        }
    }
    
    @After(value = "@browser",order = 1)
    public void browserAfter2(Scenario scenario)
    {
        epilogue2(scenario);
    }
    
    protected void epilogue2(Scenario scenario)
    {
        if(world.driver != null)
        {
            world.driver.quit();
        }
    }
    
    protected void webDriverManagerSetup()
    {
        if(webDriverManager == false)
        {
            WebDriverManager.chromedriver().setup();
            webDriverManager = true;
        }
    }
    
    protected void webDriverGet()
    {
        world.driver = new WebDriverFactory(world.config).getDriver();
        world.driver.manage().window().maximize();
    }
    
    
    

    
}
