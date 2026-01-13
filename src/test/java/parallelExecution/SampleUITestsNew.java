package parallelExecution;

import browserFactory.BrowserFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.LoginPage;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import java.io.File;
import java.time.Duration;

public class SampleUITestsNew extends BrowserFactory{
    public static WebDriver driver;

    public LoginPage loginPage;
    @DataProvider(name="userData")
    public Object[][] createUserData(){
        return new Object[][]{
                {"admin@yourstore.com","admin"}
        };
    }

    @Test(dataProvider = "userData")
    public void UITestNew(String userName, String password){
        BrowserFactory browserFactory = BrowserFactory.getInstance();
        browserFactory.setDriver("chrome");
        driver = browserFactory.getDriver();
        loginPage=new LoginPage(driver);
        String url = "https://admin-demo.nopcommerce.com/login?";
        driver.get(url);
        System.out.println("Chrome browser launched the url : " + url);
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickLogin();
        if(driver.getPageSource().contains("Login was unsuccessful. ")) {
            driver.close();
            Assert.assertTrue("test Failed",true);
        }else {
            Assert.assertEquals("Just a moment...",driver.getTitle());
        }
        loginPage.clickLogout();
        Screenshot scre = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
        Assert.assertEquals("Your store. Login",driver.getTitle());
        driver.quit();
        System.out.println("Webdriver quits the chrome browser");
    }
}
