package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
    (
            features="src/test/java/Features",
            glue={"stepdefinitions"},
            dryRun=true,
            plugin= {"pretty","html:test-output/test.html"},
            monochrome = true,
            tags = "@LoginTest"
    )

public class TestRunner1 {

}
