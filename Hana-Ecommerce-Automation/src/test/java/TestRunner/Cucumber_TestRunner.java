package TestRunner;


import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/Features"},
        glue = "StepDefinition",
        plugin = {"pretty",
                "html:reports/Ecommerce_Automation_Cucumber_Report.html",
                "rerun:target/rerun.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },

        dryRun = false,    // checks mapping between scenario steps and step definition methods
        monochrome = true,    // to avoid junk characters in output
        publish = true   // to publish report in cucumber server
        //tags="@sanity"  // this will execute scenarios tagged with @sanity
        //tags="@regression"
        //tags="@sanity and @regression" //Scenarios tagged with both @sanity and @regression
        //tags="@sanity and not @regression" //Scenarios tagged with @sanity but not tagged with @regression
        //tags="@sanity or @regression" //Scenarios tagged with either @sanity or @regression
)
public class Cucumber_TestRunner {
}
