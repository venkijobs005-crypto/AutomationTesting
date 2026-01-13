package javaPrograms;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test2 {
    static WebDriver driver;
    public static void main(String [] args) throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                        .ignoring(NoSuchElementException.class);
        driver.get("http://www.google.com/");
        driver.manage().window().maximize();
        driver.findElement(By.name("q")).sendKeys("selenium");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        List<WebElement> list = driver.findElements(By.xpath("//ul[@role='listbox']/li//span/b"));
        for(int i = 0; i<= list.size(); i++){
            list = driver.findElements(By.xpath("//ul[@role='listbox']/li//span/b"));
            if(list.get(i).getText().contains("testing")){
                JavascriptExecutor js =(JavascriptExecutor)driver;
                js.executeScript("arguments[0].style.border='3px solid red'",list.get(i));
                FileUtils.copyFile(list.get(i).getScreenshotAs(OutputType.FILE), new File("F:\\Documents_Folder\\Automation_Interview\\Selenium & POM\\tese.jpg"));
                list.get(i).submit();
                break;
            }
        }

//        for(WebElement ele : list){
//            if(ele.getText().contains("testing")){
//                JavascriptExecutor js =(JavascriptExecutor)driver;
//                js.executeScript("arguments[0].style.border='3px solid red'",ele);
//                ele.submit();
//                break;
//            }
//        }

        driver.quit();
    }

}
