package testNGListeners;

import browserFactory.BrowserFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyiTestListener implements ITestListener {
    BrowserFactory browserFactory = BrowserFactory.getInstance();
    @Override
    public void onTestFailure(ITestResult result) {
        String dir = System.getProperty("user.dir");
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmSS");
        File screenshot = ((TakesScreenshot)browserFactory.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(dir + "\\test-output\\TestFailed_" + dateFormat.format(date) +".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
