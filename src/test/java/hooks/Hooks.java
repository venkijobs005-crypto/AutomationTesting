package hooks;

import browserFactory.BrowserFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    BrowserFactory browserFactory = BrowserFactory.getInstance();
    @Before
    public void setup() {
        System.out.println("hooks executed");
        browserFactory.setDriver("Chrome");
    }

    @After
    public void teardown() {
        browserFactory.quitDriver();
    }
}
