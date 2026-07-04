@browser @report
Feature: Website Navigation and Validation

  @Test1
  Scenario: Open website and validate page loads successfully
    Given User opens the application
    When User waits for page to load
    And User validates that the page header title is not empty
