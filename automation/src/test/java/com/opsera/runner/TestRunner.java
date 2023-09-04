package com.opsera.runner;
import org.junit.runner.RunWith;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
	

	@RunWith(Cucumber.class)
	@CucumberOptions(
	        
			features = "src/test/resources/cucumber/features/LoginWithOTP.feature", 
	     	glue = "com/opsera/stepDef",
	     
	     		plugin={"pretty"
		        , "summary"
		        , "html:target/cucumber/report.html",
		        "json:target/cucumber-reports/cucumber.json"
		        
		       })
public class TestRunner extends BaseTest {
		
}
