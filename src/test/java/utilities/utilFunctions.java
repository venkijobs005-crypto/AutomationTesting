package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;


public class utilFunctions {
    public static void takeStepScreenshot(WebDriver driver, String stepName) {
        try {
            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            // Save to a folder named 'all_screenshots' in your project directory
            String path = System.getProperty("user.dir") + "/all_screenshots/" + stepName + ".png";
            FileUtils.copyFile(srcFile, new File(path));
            System.out.println("Screenshot taken for step: " + stepName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
