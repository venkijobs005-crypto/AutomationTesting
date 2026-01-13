package browserFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserFactory {
    private static BrowserFactory instance=null;
    private static final ThreadLocal<WebDriver>  driver = new ThreadLocal<>();

    protected BrowserFactory(){

    }

    //synchronized - If Thread A is inside the method, Thread B must wait
    //Only after Thread A finishes, Thread B can enter. So instance will be created only once
    public static synchronized BrowserFactory getInstance(){
        if(instance==null)
            instance = new BrowserFactory();
        return instance;
    }

    public final void setDriver(String browser) {
        ChromeOptions options = new ChromeOptions();
//        if(browser==null)
//            browser="chrome";
        switch(browser.toLowerCase()) {
            case "chrome":
                options.addArguments("--disable-plugins");
                options.addArguments("start-maximized");
                options.setBrowserVersion("124");
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
//                options.setExperimentalOption("useAutomationExtension", false);
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver(options));
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;
            case "remote":
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--headless=new");

                try {
                   WebDriver remoteWebDriver = new RemoteWebDriver(
                            new URL("http://selenium-hub:4444/wd/hub"),
                            options
                    );
                   driver.set(remoteWebDriver);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                throw new IllegalArgumentException("No matching browser");
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
    //driver.get() retrieves the WebDriver instance for the current thread from the ThreadLocal
    //ThreadLocal will keep a reference to the WebDriver even after the test finishes.
    // driver.remove() is used to remove the reference
}
