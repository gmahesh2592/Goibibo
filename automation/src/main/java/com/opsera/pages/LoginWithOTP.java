package com.opsera.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.opsera.commonfunctions.CommonFunctions;
import com.opsera.config.ConfigurationManager;

public class LoginWithOTP {

	CommonFunctions cf = new CommonFunctions();
	ConfigurationManager cm = new ConfigurationManager();

	public static By inputMobileNum = By.xpath("//input[@name='phone']");
	public static By continueBtn = By.xpath("//button[contains(text(),'Continue')]");
	public static By flightsTab = By.xpath("//p[text()='Flights']");
	public static By closebtn = By.xpath("//span[@role='presentation']");
	public static By radioRoundTripbtn = By.xpath("//p[contains(text(),'Round-trip')]/preceding-sibling::span");
	public static By inputFrom = By.xpath("//span[text()='From']/following-sibling::p[text()='Enter city or airport']");
	public static By inputTo = By.xpath("//span[text()='To']/following::input");
	public static By totalListFromToValues = By.xpath("//ul[contains(@id,'autoSuggest-list')]//li");
	public static By departureDate = By
			.xpath("//span[text()='Departure']/following-sibling::p[contains(@class,'sc-')]");
	public static By donebtn = By.xpath("//span[text()='Done']");
	public static By dayPickerCaption = By.xpath("//div[@class='DayPicker-Caption']");
	public static By adults = By.xpath("((//p[text()='Adults']/ancestor::div)[11]//span)[3]");
	public static By children = By.xpath("((//p[text()='Children']/ancestor::div)[11]//span)[3]");
	public static By infants = By.xpath("((//p[text()='Infants']/ancestor::div)[11]//span)[3]");
	public static By doneAddPeople = By.xpath("//a[text()='Done']");
	public static By searchFlightsBtn = By.xpath("//span[text()='SEARCH FLIGHTS']");
	public static By totalFlightsCount = By
			.xpath("(//div[contains(@class,'NewSortOptionsstyles__SortTitle')]/following-sibling::span)");
	public static By lastFlight = By.xpath("(//div[contains(@class,'srp-card-uistyles__ResultCard')])[last()]");
	public static By bookBtn = By.xpath("//input[@value='Book']");
	public static By captureAllDetailsEconomey = By.xpath("//table[@class='fareFamilyTable']//tr");
	public static By nextBtn = By.xpath("//button[text()='Next']");
	public static By proceedBtn = By.xpath("//button[text()='Proceed']");
	public static By okBtn = By.xpath("//button[text()='OK']");
	public static By confirmationPopup = By.xpath("//div[contains(text(),'Re-confirmation Failed With Airline...')]");
	public static By verifyConfirmBillDetails = By
			.xpath("//label[text()='Confirm and save billing details to your profile']");
	public static By confirmCheck = By.xpath("//span[contains(@class,'sc-esYiGF')]");
	public static By verifyInfantName = By.xpath("//span[contains(text(),'First')]");
	public static By verifyDateOfBirth = By.xpath("//div[text()='Date of birth is mandatory']");
	public static By verifyEmailId = By.xpath("//span[text()='Email ID cannot be empty']");
	public static By verifyMobileNum = By.xpath("//span[text()='Mobile Number cannot be empty']");
	public static By firstMiddileName=By.xpath("//span[contains(text(),'First & Middle Name is mandatory')]");
	public static By selectGender = By.xpath("//Select[contains(@class,'common-elementsstyles__CustSelectT')]");
	public static By gender = By.xpath("//option[@value='MALE']");
	public static By firstName = By.xpath("//input[@name='givenname']");
	public static By selectDay = By.xpath("(//select[contains(@class,'common-elementsstyles__CustSelectTrvl-')])[2]");
	public static By selectMonth = By.xpath("(//select[contains(@class,'common-elementsstyles__CustSelectTrvl-')])[3]");
	public static By selectYear = By.xpath("(//select[contains(@class,'common-elementsstyles__CustSelectTrvl-')])[4]");
	public static By dayOne = By.xpath("option[@value='01']");
	public static By monthOne = By.xpath("option[@value='01']");
	public static By year = By.xpath("//option[@value='2022']");
	public static By inputEmail = By.xpath("//input[@name='email']");
	public static By inputmobile = By.xpath("//input[@name='mobile']");
	public static By proceedPaymentbtn=By.xpath("//button[text()='Proceed To Payment']");
	String firstMiddleName;

	public void getPagetitle(WebDriver driver) {
		cf.getPageTitle(driver);
		cf.waitForElementPresent(closebtn, 10, driver);
		cf.doActionsClick(closebtn, driver);
	}

	public void clickFlightsbtn(WebDriver driver) {
		cf.waitForElementPresent(flightsTab, 10, driver);
		cf.doClick(flightsTab, driver);
		cf.waitForElementPresent(radioRoundTripbtn, 10, driver);
		cf.doClick(radioRoundTripbtn, driver);
	}

	public void inputFlightFrom(WebDriver driver) {
		cf.waitForElementPresent(inputFrom, 10, driver);
		cf.doActionsSendKeys(inputFrom, cm.getProperty("InputFrom"), driver);
		flightsFrom(driver);
	}

	public void flightsFrom(WebDriver driver) {
		List<WebElement> totalCountToValues = driver
				.findElements(By.xpath("//ul[contains(@id,'autoSuggest-list')]//li"));
		System.out.println("To City list size:" + totalCountToValues.size());
		
		for (WebElement e : totalCountToValues) {
			System.out.println("Total City values:"+e.getText());
			if (e.getText().contains(cm.getProperty("InputFrom"))) {
				e.click();
				break;
			}
		}
	}

	public void inputFlightTo(WebDriver driver) {
		cf.waitForElementPresent(inputTo, 10, driver);
		cf.doActionsSendKeys(inputTo, cm.getProperty("InputTo"), driver);
		flightsTo(driver);
	}

	public void flightsTo(WebDriver driver) {
		List<WebElement> totalCountToValues = driver
				.findElements(By.xpath("//ul[contains(@id,'autoSuggest-list')]//li"));
		System.out.println("To City list size:" + totalCountToValues.size());
		for (WebElement e : totalCountToValues) {
			if (e.getText().contains(cm.getProperty("InputTo"))) {
				e.click();
				break;
			}
		}
	}

	public void inputDepartureDate(WebDriver driver) {
		List<WebElement> totalDaypickerCaption = driver.findElements(By.xpath("//div[@class='DayPicker-Caption']"));
		System.out.println("Day picker Caption size:" + totalDaypickerCaption.size());
		for (int i = 1; i <= totalDaypickerCaption.size(); i++) {
			if (cm.getProperty("DayPickerCaptionDep")
					.contains(driver.findElement(By.xpath("(//div[@class='DayPicker-Caption'])[" + i + "]")).getText()))
				;
			WebElement clickNextbtn = driver.findElement(By.xpath("//span[@aria-label='Next Month']"));
			clickNextbtn.click();
			driver.findElement(By.xpath("(//div[@class='DayPicker-Body']//p[text()='1'])[2]")).click();
			break;
		}
		inputReturnDate(driver);
	}

	public void inputReturnDate(WebDriver driver) {
		List<WebElement> totalDaypickerCaption = driver.findElements(By.xpath("//div[@class='DayPicker-Caption']"));
		System.out.println("Day picker Caption size:" + totalDaypickerCaption.size());
		for (int i = 1; i <= totalDaypickerCaption.size(); i++) {
			if (cm.getProperty("DayPickerCaptionRet")
					.contains(driver.findElement(By.xpath("(//div[@class='DayPicker-Caption'])[" + i + "]")).getText()))
				;
			WebElement clickNextbtn = driver.findElement(By.xpath("//span[@aria-label='Next Month']"));
			clickNextbtn.click();
			driver.findElement(By.xpath("(//div[@class='DayPicker-Body']//p[text()='1'])[2]")).click();
			break;
		}
	}

	public void clickDonebtn(WebDriver driver) {
		cf.waitForElementPresent(donebtn, 10, driver);
		cf.doClick(donebtn, driver);
	}

	public void addAdults(WebDriver driver) {
		for (int i = 1; i < 3; i++) {
			cf.doClick(adults, driver);
		}
	}

	public void addChildrens(WebDriver driver) {
		for (int i = 0; i < 2; i++) {
			cf.doClick(children, driver);
		}
	}

	public void addInfants(WebDriver driver) {
		for (int i = 0; i < 1; i++) {
			cf.doClick(infants, driver);
		}
	}

	public void clickDoneAddPeople(WebDriver driver) {
		cf.waitForElementPresent(doneAddPeople, 10, driver);
		cf.doClick(doneAddPeople, driver);
	}

	public void clickSearchFlightsBtn(WebDriver driver) {
		cf.waitForElementPresent(searchFlightsBtn, 10, driver);
		cf.doClick(searchFlightsBtn, driver);
		cf.waitForElementToBeClickable(totalFlightsCount, 15, driver);
	}

	public void showingFlightsCountDepRet(WebDriver driver) throws Exception {
		List<WebElement> totalCountFlightDepRet = driver.findElements(
				By.xpath("(//div[contains(@class,'NewSortOptionsstyles__SortTitle')]/following-sibling::span)"));
		System.out.println("To City list size:" + totalCountFlightDepRet.size());
		for (WebElement e : totalCountFlightDepRet) {
			System.out.println("showing total count of flights both departure and return :" + e.getText());
		}
		getFlightsNames(driver);
	}

	public void getAllFlightDetails(WebDriver driver) throws Exception {
		StringBuffer s  = new StringBuffer();
		String s1="";
		for (int i = 0; i < 9; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,2000)", "");
			Thread.sleep(5000);
		}
		List<WebElement> allFlightDetails = driver
				.findElements(By.xpath("//div[contains(@class,'srp-card-uistyles__ResultCard')]"));
		System.out.println(
				"Total flight count of flights both departure and return  list size:" + allFlightDetails.size());
		
		for (WebElement e : allFlightDetails) {
			String s3=e.getText().replace("\n", "").replace("Flight Details", "").replace("Free Meal", "").replace("Lock Price", "");
			System.out.println(s3);
		}
		
	}
	public void getFlightsNames(WebDriver driver) throws Exception {
		for (int i = 0; i < 9; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,2000)", "");
			Thread.sleep(5000);
		}
		List<WebElement> allFlightNames = driver
				.findElements(By.xpath("//span[contains(@class,'padR10')]"));
		System.out.println("total filghts names :"+ allFlightNames.size());
		for (WebElement e : allFlightNames) {
			System.out.println(e.getText());
		}
		
		
		
	}

	public void getLastflight(WebDriver driver) throws Exception {
		for (int i = 0; i < 5; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)", "");
			Thread.sleep(5000);
		}
		cf.doActionsClick(lastFlight, driver);
		cf.doActionsClick(bookBtn, driver);

	}

	public void getAllCaptureEconomyDetails(WebDriver driver) {
		List<WebElement> allEconomyDetails = driver.findElements(By.xpath("//table[@class='fareFamilyTable']//tr"));
		System.out.println("list and capture all details for Economy:" + allEconomyDetails.size());
		for (WebElement e : allEconomyDetails) {
			System.out.println("capture all details for Economy :" + e.getText());
		}
		cf.doActionsClick(nextBtn, driver);
		cf.doActionsClick(proceedBtn, driver);

	}

	public void verifyFareSummary(WebDriver driver) throws Exception {
		// WebElement fareSummaryDetails= driver
		// .findElement(By.xpath("//div[contains(text(),'ADULTS ')]"));
		// System.out.println("Get Fare summary details:" +
		// fareSummaryDetails.getText());

		getWindowHandlesOne(driver);
		reviewYourBooking(driver);
		cf.doActionsClick(proceedBtn, driver);
		Thread.sleep(2000);
		cf.waitForElementPresent(confirmCheck, 10, driver);
		cf.doActionsClick(confirmCheck, driver);
		cf.doActionsClick(proceedBtn, driver);

	}

	public void getWindowHandlesOne(WebDriver driver) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("Count tab:" + tabs.size());
		driver.switchTo().window(tabs.get(1));
	}

	public void getWindowHandlesTwo(WebDriver driver) {
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("Count tab:" + tabs2.size());
		driver.switchTo().window(tabs2.get(2));
	}

	public void reviewYourBooking(WebDriver driver) throws Exception {
		try {
			boolean confirmartionMsgPresence = driver
					.findElement(By.xpath("//div[contains(text(),'Re-confirmation Failed With Airline...')]"))
					.isDisplayed();

			boolean okBtnEnabled = driver.findElement(By.xpath("//button[text()='OK']")).isDisplayed();
			if (confirmartionMsgPresence == true && okBtnEnabled == true) {
				cf.doActionsClick(LoginWithOTP.okBtn, driver);
				getAllFlightDetails(driver);
				getLastflight(driver);
				cf.doActionsClick(bookBtn, driver);
				getAllCaptureEconomyDetails(driver);
				cf.doActionsClick(LoginWithOTP.proceedBtn, driver);
				getWindowHandlesTwo(driver);
				reviewYourBooking(driver);
				cf.doActionsClick(LoginWithOTP.proceedBtn, driver);
				verifyconfirmDetails(driver);
				cf.waitForElementPresent(confirmCheck, 10, driver);
				cf.doActionsClick(confirmCheck, driver);
				cf.doActionsClick(LoginWithOTP.proceedBtn, driver);

			}
			boolean proceedBtn = driver.findElement(By.xpath("//button[text()='Proceed']")).isDisplayed();
			if (proceedBtn == true) {
				cf.doActionsClick(LoginWithOTP.proceedBtn, driver);
				verifyconfirmDetails(driver);
				cf.waitForElementPresent(confirmCheck, 10, driver);
				cf.doActionsClick(confirmCheck, driver);
				cf.doActionsClick(confirmCheck, driver);
				cf.doActionsClick(LoginWithOTP.proceedBtn, driver);

			}

		} catch (Exception e) {

			// TODO: handle exception
		}
	}

	public void verifyconfirmDetails(WebDriver driver) {
		cf.doGetText(verifyConfirmBillDetails, driver);
	}

	public void inputInfantDetails(WebDriver driver) {
		Actions act = new Actions(driver);
		cf.doGetText(verifyInfantName, driver);
		cf.doSendKeys(firstName, cm.getProperty("infantName"), driver);
		cf.doActionsClick(selectGender, driver);
		cf.doSelectByValue(gender, cm.getProperty("infantMale"), driver);
		String validDatebirth = cf.doGetText(verifyDateOfBirth, driver);
		Assert.assertEquals("Date of birth is mandatory", validDatebirth);
		cf.doActionsClick(selectDay, driver);
		cf.doSelectByValue(dayOne, cm.getProperty("InputDay"), driver);
		cf.doActionsClick(selectMonth, driver);
		cf.doSelectByValue(monthOne, cm.getProperty("InputMonth"), driver);
		cf.doActionsClick(selectYear, driver);
		cf.doSelectByValue(year, cm.getProperty("InputYear"), driver);
		String emailAdd = cf.doGetText(verifyEmailId, driver);
		Assert.assertEquals("Email ID cannot be empty", emailAdd);
		cf.doSendKeys(inputEmail, cm.getProperty("EmailAddress"), driver);
		String mobileText = cf.doGetText(verifyMobileNum, driver);
		Assert.assertEquals("Mobile Number cannot be empty", mobileText);
		cf.doSendKeys(inputmobile, cm.getProperty("MobileNumber"), driver);
		cf.doActionsClick(LoginWithOTP.proceedBtn, driver);
	}
	public void inputMandtoryfields(WebDriver driver) {
		Actions act = new Actions(driver);
		String firstMiddleName=cf.doGetText(firstMiddileName, driver);
		Assert.assertEquals("First & Middle Name is mandatory", firstMiddleName);
		cf.doSendKeys(firstName, cm.getProperty("infantName"), driver);
		cf.doActionsClick(selectGender, driver);
		cf.doSelectByValue(gender, cm.getProperty("infantMale"), driver);
		cf.doActionsClick(LoginWithOTP.proceedBtn, driver);
		 firstMiddleName=cf.doGetText(firstMiddileName, driver);
		Assert.assertEquals("First & Middle Name is mandatory", firstMiddleName);
		cf.doSendKeys(firstName, cm.getProperty("infantName"), driver);
		cf.doActionsClick(selectGender, driver);
		cf.doSelectByValue(gender, cm.getProperty("infantMale"), driver);
		cf.doActionsClick(LoginWithOTP.proceedBtn, driver);
		firstMiddleName=cf.doGetText(firstMiddileName, driver);
		Assert.assertEquals("First & Middle Name is mandatory", firstMiddleName);
		cf.doSendKeys(firstName, cm.getProperty("infantName"), driver);
		cf.doActionsClick(selectGender, driver);
		cf.doSelectByValue(gender, cm.getProperty("infantMale"), driver);
		cf.doActionsClick(LoginWithOTP.proceedBtn, driver);
		firstMiddleName=cf.doGetText(firstMiddileName, driver);
		Assert.assertEquals("First & Middle Name is mandatory", firstMiddleName);
		cf.doSendKeys(firstName, cm.getProperty("infantName"), driver);
		cf.doActionsClick(selectGender, driver);
		cf.doSelectByValue(gender, cm.getProperty("infantMale"), driver);
		cf.doActionsClick(LoginWithOTP.proceedBtn, driver);
		firstMiddleName=cf.doGetText(firstMiddileName, driver);
		Assert.assertEquals("First & Middle Name is mandatory", firstMiddleName);
		cf.doSendKeys(firstName, cm.getProperty("infantName"), driver);
		cf.doActionsClick(selectGender, driver);
		cf.doSelectByValue(gender, cm.getProperty("infantMale"), driver);
		cf.doActionsClick(LoginWithOTP.proceedBtn, driver);
	}
	
public void clickProceddBtn(WebDriver driver) {
	cf.doActionsClick(proceedPaymentbtn, driver);
}
}
