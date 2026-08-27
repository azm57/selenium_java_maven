package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterSuite;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "Features",
		glue = { "StepDefinition" },
		stepNotifications = true,
		dryRun = false,
		monochrome = false,
		plugin = {"pretty", "html:target/cucumber.html"},
		tags = "@smoke")
public class TestRunner {
	//sample
}
