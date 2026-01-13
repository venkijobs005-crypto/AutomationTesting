Feature: Login

  Background:
    Given user launches the chrome browser
    When user opens the url "https://admin-demo.nopcommerce.com/login?"

  @LoginTest
  Scenario: User Login with valid credentials - Scenario 1
    And user enters email as "admin@yourstore.com" and password as "admin"
    And user clicks on login
    Then Page title should be "Just a moment..."

  @ParallelExecution
  Scenario: User Login with valid credentials - Scenario 2
    And user enters email as "admin@yourstore.com" and password as "admin"
    And user clicks on login
    Then Page title should be "Just a moment..."

  @ParallelExecution
  Scenario: User Login with valid credentials - Scenario 3
    And user enters email as "admin@yourstore.com" and password as "admin"
    And user clicks on login
    Then Page title should be "Just a moment..."

  @ParallelExecution
  Scenario: User Login with valid credentials - Scenario 4
    And user enters email as "admin@yourstore.com" and password as "admin"
    And user clicks on login
    Then Page title should be "Just a moment..."
