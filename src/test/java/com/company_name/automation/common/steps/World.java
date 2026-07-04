package com.company_name.automation.common.steps;

import com.company_name.automation.common.utilities.DataBase;
import org.apache.logging.log4j.core.config.composite.CompositeConfiguration;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class World {
    public WebDriver driver;
    public DataBase db_instance;
    public CompositeConfiguration context;
    public Properties config;
    public StepReportData report;

    public World() throws IOException {
        report = new StepReportData();
        config = new Properties();
        config.load(new FileInputStream("./default.properties"));
    }

}
