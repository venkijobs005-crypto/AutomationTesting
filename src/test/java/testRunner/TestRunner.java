package testRunner;
import browserFactory.BrowserFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
        (
                features = "src/test/resources/features",
                glue={"stepdefinitions"},
                plugin = {
                        "pretty",
                        "json:target/cucumber-report.json",
                        "html:target/cucumber-html-report"
                },
                monochrome = true,
                tags = "@ParallelExecution"
        )
public class TestRunner extends AbstractTestNGCucumberTests {
    static BrowserFactory browserFactory = BrowserFactory.getInstance();
    @DataProvider(parallel=true)
    @Override
    public Object[][] scenarios(){
        return super.scenarios();
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Running before suite");
        //ExtentManager.initReports();
        //DatabaseConnection.init();
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("Running after suite");
        //ExtentManager.flushReports();
        //DatabaseConnection.close();
    }

    @BeforeClass
    public void beforeClass(ITestContext context) {
        System.out.println("Running before class");
        context.getCurrentXmlTest().getSuite().setDataProviderThreadCount(3);
    }

    @AfterClass
    public void afterClass() {
        System.out.println("Running after class");
    }

    @BeforeMethod
    public void beforeMethod(){
        // Browser parameter is picked from TestNG XML → System.getProperty
        String browser = System.getProperty("browser");
        browserFactory.setDriver(browser);
    }

    @AfterMethod
    public void afterMethod(){
        browserFactory.quitDriver();
    }
}
