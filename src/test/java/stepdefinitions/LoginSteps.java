package stepdefinitions;

import browserFactory.BrowserFactory;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObject.LoginPage;

public class LoginSteps {
    BrowserFactory browserFactory = BrowserFactory.getInstance();
    public WebDriver driver;
    public LoginPage loginPage;

    @Given("^user launches the chrome browser$")
    public void user_launches_the_chrome_browser() {
        System.out.println("Navigating to login page");
        driver = browserFactory.getDriver();
        loginPage=new LoginPage(driver);

    }

    @When("^user opens the url \"([^\"]*)\"$")
    public void user_opens_the_url(String url) {
        driver.get(url);
        System.out.println("Chrome browser launched the url : " + url);
        utilities.utilFunctions.takeStepScreenshot(driver,"launched");
    }

    @When("^user enters email as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void user_enters_email_as_and_password_as(String userName, String password) {
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        utilities.utilFunctions.takeStepScreenshot(driver,"logged_in");
    }

    @When("^user clicks on login$")
    public void user_clicks_on_login() {
        loginPage.clickLogin();
    }

    @Then("^Page title should be \"([^\"]*)\"$")
    public void page_title_should_be(String title)  {
        if(driver.getPageSource().contains("Login was unsuccessful. ")) {
            utilities.utilFunctions.takeStepScreenshot(driver,"logged_in_success");
            driver.close();
        }else {
            Assert.assertEquals(title,driver.getTitle());
        }
    }

    @Before("@LoginTest")
    public void precondition(){

    }

    @When("^user clicks on logout link$")
    public void user_clicks_on_logout_link() {
        loginPage.clickLogout();
    }

}
