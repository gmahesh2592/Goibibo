package com.opsera.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
private static WebDriver driver;
	
	public enum BrowserType {
        CHROME, FIREFOX, EDGE
    }

	public static WebDriver getDriver(BrowserType browserType) {
        if (driver == null) {
            switch (browserType) {
                case CHROME:
                    driver = createChromeDriver();
                    break;
                case FIREFOX:
                    driver = createFirefoxDriver();
                    break;
                case EDGE:
                    driver = createEdgeDriver();
                    break;
                default:
                	driver = createChromeDriver();
                	//throw new IllegalArgumentException("Invalid browser type: " + browserType);
            }
        }
        return driver;
        
	}
  
	private static WebDriver createChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
		options.addArguments("--window-size=1920,1080");

		options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    }
	
	private static WebDriver createFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        // Add desired Firefox options if needed
        // options.setHeadless(true);
        return new FirefoxDriver(options);
    }
	
	private static WebDriver createEdgeDriver() {
        System.setProperty("webdriver.edge.driver", "path/to/edgedriver");
        EdgeOptions options = new EdgeOptions();
        // Add desired Firefox options if needed
        // options.setHeadless(true);
        return new EdgeDriver(options);
    }
	public static WebDriver getDriver() {
		if (driver == null) {

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			//options.addArguments("--window-size=1920,1080");

			options.addArguments("--remote-allow-origins=*");
			System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver.exe");

			driver = new ChromeDriver(options);
		}
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

}
