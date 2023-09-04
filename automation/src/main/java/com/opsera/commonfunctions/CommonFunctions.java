package com.opsera.commonfunctions;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;



import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opsera.config.DriverFactory;

public class CommonFunctions {
	

		 HashMap<String, String> scrollObject;

		public WebElement getElement(By locator, WebDriver driver) {
			WebElement element = null;
			try {
				System.out.println("locator is : " + locator);
				element = (WebElement) driver.findElement(locator); 

				System.out.println("WebElement is created successfully : " + locator);

			} catch (Exception e) {
				System.out.println("some exception got occurred with this locator: " + locator);
			}
			return element;
		}

		public void doSendKeys(By locator, String value, WebDriver driver) {
			waitForElementPresent(locator, 10, driver);
			getElement(locator, driver).sendKeys(value);
		}
	    
		public String getPageTitle(WebDriver driver) {
			return driver.getTitle();
		}
		public void doClick(By locator, WebDriver driver) {

			waitForElementPresent(locator, 10, driver);
			getElement(locator, driver).click();
		}
		

		public String doGetText(By locator, WebDriver driver) {
			waitForElementPresent(locator, 10, driver);
			return getElement(locator, driver).getText().trim();
		}

		public boolean doIsDisplayed(By locator, WebDriver driver) {
			waitForElementPresent(locator, 10, driver);	
			return getElement(locator, driver).isDisplayed();
		}
		
		public boolean doClickable(By locator, WebDriver driver) {
			waitForElementPresent(locator, 10, driver);	
			return getElement(locator, driver).isEnabled();
		}
		public void clickJavascriptExecutor(By locator, WebDriver driver) {
			JavascriptExecutor js = (JavascriptExecutor)driver; {
				js.executeScript("arguments[0].click();", locator);		
			}
		}

		public void doClear(WebElement element) {
			element.clear();
		} 
		
		public void doClear(By locator, WebDriver driver) {
			driver.findElement(locator).clear();
		} 
		
		
		

		public void doubleClickOnElement(WebElement element) {
			new Actions(DriverFactory.getDriver()).moveToElement(element).doubleClick().perform();
		}

		public void enterTextUsingActions(By locator, String value, WebDriver driver) {
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(locator)).sendKeys(value).build().perform();
		}
		public void clickSingleActions(By locator, WebDriver driver) throws Exception {
			Actions act = new Actions(driver);
			Thread.sleep(500);
			act.moveToElement(driver.findElement(locator)).build().perform();
		}
		public void doubleClickActions(By locator, WebDriver driver) {
			Actions act = new Actions(driver);
			act.doubleClick(driver.findElement(locator)).build().perform();
		}
		public void enterSendkeysEnter(By locator, WebDriver driver) {
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(locator)).sendKeys(Keys.ENTER).build().perform();
		}

		
		
		public void jsScrollInToView(WebDriver driver, List<WebElement> element) {
			JavascriptExecutor js = (JavascriptExecutor) element;
			js.executeScript("arguments[0].scrollIntoView();", driver);
		}

		

		protected void moveMouseToElement(WebElement element, int x_offset, int y_offset) {
			new Actions(DriverFactory.getDriver()).moveToElement(element, x_offset, y_offset).perform();
		}
		
		
		
		
		public void enterValueAndPressEnter(WebElement element, String value) {
			doClear(element);
			element.sendKeys(value, Keys.ENTER);
		}

		// ***************************** Wait Utils

		public List<WebElement> visibilityofAllElements(By locator, int timeout, WebDriver driver) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}

		public WebElement waitForElementPresent(By locator, int timeout, WebDriver driver) {

			WebElement element = (WebElement) driver.findElement(locator);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			element = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return element;
		}

		public WebElement waitForElementToBeVisible(By locator, int timeout, WebDriver driver) {
			WebElement element = getElement(locator, driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.visibilityOf(element));
			return element;
		}

		public WebElement waitForElementToBeClickable(By locator, int timeout, WebDriver driver) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			return element;
		}

		public boolean waitForUrl(String url, int timeout, WebDriver driver) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			return wait.until(ExpectedConditions.urlContains(url));
		}

		public Alert waitForAlertToBePresent(int timeout, WebDriver driver) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			return alert;
		}

		// clickWhenReady:
		public void clickWhenReady(By locator, int timeout, WebDriver driver) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			element.click();
		}

		public String waitForTitleToBePresent(String title, int timeout, WebDriver driver) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.titleContains(title));
			return driver.getTitle();
		}

		// **********************************Actions class Utils
		// *********************************

		public void doDragAndDrop(By source, By target, WebDriver driver) {
			Actions action = new Actions(driver);
			WebElement sourceEle = getElement(source, driver);
			WebElement targetEle = getElement(target, driver);
			action.dragAndDrop(sourceEle, targetEle).build().perform();
		}

		public void doActionsSendKeys(By locator, String value, WebDriver driver) {
			Actions action = new Actions(driver);
			action.sendKeys(getElement(locator, driver), value).build().perform();
		}

		public void doActionsClick(By locator, WebDriver driver) {
			waitForElementPresent(locator, 10, driver);
			Actions action = new Actions(driver);
			action.click(getElement(locator, driver)).build().perform();
		}
		// **********************************Drop Down Utils

		public void doSelectByVisibleText(By locator, String value, WebDriver driver) {
			Select select = new Select(getElement(locator, driver));
			select.selectByVisibleText(value);
		}

		public void doSelectByIndex(By locator, int index, WebDriver driver) {
			Select select = new Select(getElement(locator, driver));
			select.selectByIndex(index);
		}

		public void doSelectByValue(By locator, String value, WebDriver driver) {
			Select select = new Select(getElement(locator, driver));
			select.selectByValue(value);
		}

		public int doDropDownOptionsCount(By locator, WebDriver driver) {
			return doGetDropDownOptions(locator, driver).size();
		}

		public ArrayList<String> doGetDropDownOptions(By locator, WebDriver driver) {
			ArrayList<String> ar = new ArrayList<String>();
			Select select = new Select(getElement(locator, driver));
			List<WebElement> OptionsList = select.getOptions();

			for (int i = 0; i < OptionsList.size(); i++) {
				String text = OptionsList.get(i).getText();
				ar.add(text);
			}
			return ar;
		}

		public void doSelectDropDownValue(By locator, String value, WebDriver driver) {
			Select selectday = new Select(getElement(locator, driver));
			List<WebElement> OptionsList = selectday.getOptions();

			for (int i = 0; i < OptionsList.size(); i++) {
				String text = OptionsList.get(i).getText();
				if (text.equals(value)) {
					OptionsList.get(i).click();
					break;
				}
			}
		}
		public String doGetValue(By locator, WebDriver driver) {
	        waitForElementPresent(locator, 10, driver);
	        return getElement(locator, driver).getAttribute("content-desc");
	    }

		public void doTab(By locator, WebDriver driver) {
			waitForElementPresent(locator, 10, driver);
			//driver.findElement(locator).sendKeys(Keys.ENTER);
			driver.findElement(locator).sendKeys(Keys.TAB.TAB);
			
		}
		
		public void doWindowScroll(WebDriver driver) {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,2000)", "");
			
		}
		
		public void getActiveWindowhandles(WebDriver driver) {
			Set<String> s =driver.getWindowHandles();
			Iterator<String> I1= s.iterator();

			while(I1.hasNext())
			{

			System.out.println(I1.next());
			
		}
		
		}


}
