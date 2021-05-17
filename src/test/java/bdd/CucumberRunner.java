package bdd;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    tags = "@mandatory",
    features = {"src/test/resources/bdd"},
    glue = {"bdd.stepdefs"},
    plugin = {
        "pretty:output/logs",
        "html:output/report.html",
        "json:output/report.json",
        "junit:output/report.junit"
    }
)

/*
Normally these tests would sit outside the service/implementation
but for convenience I have embedded here.
 */

public class CucumberRunner {}
