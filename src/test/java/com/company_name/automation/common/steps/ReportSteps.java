package com.company_name.automation.common.steps;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

public class ReportSteps extends Steps {

    public ReportSteps(World world) {
        super(world);
    }

    @AfterStep("@report")
    public void epilogue(Scenario scenario)
    {
            world.report.build(scenario);
    }
}
