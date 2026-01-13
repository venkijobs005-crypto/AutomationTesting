package sampleTests;

import browserFactory.BrowserFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ITestListenerTest {
    BrowserFactory browserFactory = BrowserFactory.getInstance();

    @Test
    public void UITest(){
        browserFactory.setDriver("chrome");
        browserFactory.getDriver();
        String url = "https://admin-demo.nopcommerce.com/login?";
        browserFactory.getDriver().get(url);
        System.out.println("Chrome browser launched the url : " + url);
        browserFactory.getDriver().findElement(By.xpath("//input[@id='Email']")).clear();
        browserFactory.getDriver().findElement(By.xpath("//input[@id='Email']")).sendKeys("admin@yourstore.com");
        browserFactory.getDriver().findElement(By.xpath("//input[@id='Password']")).clear();
        browserFactory.getDriver().findElement(By.xpath("//input[@id='Password']")).sendKeys("admin");
        browserFactory.getDriver().findElement(By.xpath("//button[text()='Log in']")).click();

        if(browserFactory.getDriver().getPageSource().contains("Login was unsuccessful. ")) {
            browserFactory.getDriver().close();
            Assert.assertTrue(false);
        }else {
            Assert.assertEquals("Dashboard / nopCommerce administration",browserFactory.getDriver().getTitle());
        }
        browserFactory.getDriver().findElement(By.xpath("//a[text()='Logout']")).click();
        //here it will fail
        Assert.assertEquals("Your store. Loginssss",browserFactory.getDriver().getTitle());
    }
}
