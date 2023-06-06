package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/endtoend_features",
        glue = {"stepdefinitions","hooks"},
        tags = "@e2e",//Çalışacak scenarioları belirtir.
        dryRun = false

)
public class Runner {
}
