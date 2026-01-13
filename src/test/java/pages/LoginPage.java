package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By txtUser = By.id("username");
    By txtPassword = By.id("password");
    By btnLogin = By.id("login");

    public void openSite() {
        driver.get("https://example.com/login");
    }

    public void enterUsername(String user) {
        driver.findElement(txtUser).sendKeys(user);
    }

    public void enterPassword(String pwd) {
        driver.findElement(txtPassword).sendKeys(pwd);
    }

    public void clickLogin() {
        driver.findElement(btnLogin).click();
    }
}
