package com.purna.example.runners;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber-reports"}, features = {"src/test/resources/com/purna/example/features/WeatherAPI.feature"},
    glue = {"com/purna/example/steps"})
public class RunApiFeatures {

}
