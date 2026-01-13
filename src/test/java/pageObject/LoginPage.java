package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="Email")
    @CacheLookup
    WebElement txtEMail;

    @FindBy(id="Password")
    @CacheLookup
    WebElement txtPassword;

    @FindBy(xpath = "//button[text()='Log in']")
    @CacheLookup
    WebElement btnLogin;

    @FindBy(xpath="//a[text()='Logout']")
    @CacheLookup
    WebElement lnkLogout;

    public void setUserName(String userName) {
        txtEMail.clear();
        txtEMail.sendKeys(userName);
    }

    public void setPassword(String password) {
        txtPassword.clear();
        txtPassword.sendKeys(password);

    }

    public void clickLogin() {
        btnLogin.click();
    }

    public void clickLogout() {
        lnkLogout.click();
    }
}
