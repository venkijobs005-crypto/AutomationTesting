package parallelExecution;

import browserFactory.BrowserFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import testNGListeners.MyiRetryAnalyzer;

import java.io.FileInputStream;
import java.time.Duration;

public class SampleUITests {
    public WebDriver driver;
    int i = 0;
    static BrowserFactory browserFactory = BrowserFactory.getInstance();

    @BeforeMethod
    public void setupBrowser(){
        browserFactory.setDriver("chrome");
        driver = browserFactory.getDriver();
    }

    @DataProvider(name="userData")
    public Object[][] createUserData(){
        return new Object[][]{
                {"admin@yourstore.com","admin"},
                {"admin@yourstore.com","admin"}
        };
    }

//    @DataProvider(name="userData")
    public Object[][] getUserData() throws Exception{
        String excelPath = "src/test/resources/testdata.xlsx";
        FileInputStream fis = new FileInputStream(excelPath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows - 1][cols];

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
            }
        }

        workbook.close();
        fis.close();
        return data;
    }

//    @Test(dataProvider = "userData",retryAnalyzer = MyiRetryAnalyzer.class)
    @Test(dataProvider = "userData")
    public void UITest(String userName, String password){
//        i++;
        JavascriptExecutor js =(JavascriptExecutor)driver;
        String url = "https://admin-demo.nopcommerce.com/login?";
        String urlStr = "window.location = \'" + url + "\'";
        driver.get(url);
//        js.executeScript(urlStr);
//        driver.get(url);
        System.out.println("Chrome browser launched the url : " + url);
        driver.findElement(By.xpath("//input[@id='Email']")).clear();
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@id='Password']")).clear();
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        String title = (String) js.executeScript("return document.title");
        System.out.println(title);
//        js.executeScript("history.go(0)");
//        if(i==2)
//            Assert.assertEquals("nopCommerce demo store. Login",driver.getTitle());
//        else
//            Assert.assertEquals("tttt",browserFactory.getDriver().getTitle());
        browserFactory.quitDriver();
    }

    @Test(dataProvider = "userData", alwaysRun = true)
    public void UITest1(String userName, String password){
        String url = "https://admin-demo.nopcommerce.com/login?";
        driver.get(url);
        System.out.println("Chrome browser launched the url : " + url);
        driver.findElement(By.xpath("//input[@id='Email']")).clear();
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@id='Password']")).clear();
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
//        Assert.assertEquals("nopCommerce demo store. Login",driver.getTitle());
        browserFactory.quitDriver();
    }
}

