package com.company_name.automation.common.steps;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StepReportData {
//    private ExtentReports extent;
//    private ExtentTest currentTest;

//    public StepReportData()
//    {
//        init("target/reports/extent/HtmlReport/ExtentHtml.html");
//    }
//
//    public void init(String reportPath)
//    {
//        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
//        extent = new ExtentReports();
//        extent.attachReporter(spark);
//    }

    class ReportItem{
        String text;
        byte[] screenshot;
    }

    public List<ReportItem> items = new ArrayList<>();

    public StepReportData captureScreenshot(WebDriver driver, String text) {
        try{
//            String screenshotDir = "target/reports/screenshots/";
//            new File(screenshotDir).mkdirs();
//            String timestamp = String.valueOf(System.currentTimeMillis());
//            String fileName = "../screenshots/" + timestamp + ".png";
//            String fullPath = screenshotDir + timestamp + ".png";
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

//            Files.write(Paths.get(fullPath), screenshot);


            ReportItem item = new ReportItem();
            item.text = text;
            item.screenshot = screenshot;
//            item.fileName = fileName;
            items.add(item);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public StepReportData log(String text)
    {
        ReportItem item = new ReportItem();
        item.text = text;
        items.add(item);
        return this;
    }

    public void build(Scenario scenario) {

        for (ReportItem item : items) {

            if (item.screenshot != null) {

                scenario.attach(
                        item.screenshot,
                        "image/png",
                        item.text != null ? item.text : "Screenshot"
                );

            } else if (item.text != null) {

                scenario.log(item.text);
            }
        }

        items.clear();
    }

//    public void flush() {
//        extent.flush();
//    }
}
