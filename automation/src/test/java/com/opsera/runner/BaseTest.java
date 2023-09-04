package com.opsera.runner;
import java.time.Duration;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import com.opsera.config.ConfigurationManager;
import com.opsera.config.DriverFactory;




public class BaseTest {
	
	static ConfigurationManager configurationManager = new ConfigurationManager();
	static WebDriver driver = DriverFactory.getDriver();
	
	@BeforeClass
	public static void beforeSuiteSetup() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(configurationManager.getProperty("URL"));
		
		
	}
	
	@AfterClass
	public static void afterSuite() {
		DriverFactory.quitDriver();
		
		
	}
	
    

}
