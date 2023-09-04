package com.opsera.stepDef;

import org.openqa.selenium.WebDriver;

import com.opsera.config.ConfigurationManager;
import com.opsera.config.DriverFactory;
import com.opsera.pages.LoginWithOTP;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginWithOTPStepDef {

	static ConfigurationManager configurationManager = new ConfigurationManager();
	static WebDriver driver = DriverFactory.getDriver();
	LoginWithOTP lwp = new LoginWithOTP();

	@Given("user is in login page")
	public void user_is_in_login_page() {
		lwp.getPagetitle(driver);
	}

	@When("Click on Flights")
	public void click_on_flights() {
		lwp.clickFlightsbtn(driver);
	}
	@Then("enter from value")
	public void enter_from_value() {
		lwp.inputFlightFrom(driver);
	}
	@Then("enter to value")
	public void enter_to_value() {
		lwp.inputFlightTo(driver);
	}

	@When("select departure date")
	public void select_departure_date() {
		lwp.inputDepartureDate(driver);
	}

	@Then("Click on done btn")
	public void click_on_done_btn() {
		lwp.clickDonebtn(driver);
	}

	@Then("Click on adults")
	public void click_on_adults() {
		lwp.addAdults(driver);
	}

	@Then("Click on children")
	public void click_on_children() {
		lwp.addChildrens(driver);

	}

	@Then("click on infants")
	public void click_on_infants() {
		lwp.addInfants(driver);

	}

	@Then("click on done Add people")
	public void click_on_done_add_people() {
		lwp.clickDoneAddPeople(driver);
	}

	@Then("click on search Flights btn")
	public void click_on_search_flights_btn() {
		lwp.clickSearchFlightsBtn(driver);
	}

	@Then("showing total number flights for departure and return")
	public void showing_total_number_flights_for_departure_and_return() throws Exception {
		lwp.showingFlightsCountDepRet(driver);
	}

	@Then("Get all the flight details")
	public void get_all_the_flight_details() throws Exception {
		lwp.getAllFlightDetails(driver);
	}

	@When("Select last flight")
	public void select_last_flight() throws Exception {
		lwp.getLastflight(driver);
	}

	@Then("list and capture all details for Economy")
	public void list_and_capture_all_details_for_Economy() {
		lwp.getAllCaptureEconomyDetails(driver);

	}

	@Then("verify fare summary")
	public void verify_fare_summary() throws Exception {
		lwp.verifyFareSummary(driver);

	}


	@When("Verify all error messages in the page")
	public void verify_all_error_messages_in_the_page() {
		lwp.inputInfantDetails(driver);
	}

	@Then("Enter all mandatory fields in the page and click on Proceed")
	public void enter_all_mandatory_fields_in_the_page_and_click_on_proceed() {
		lwp.inputMandtoryfields(driver);
	}

	@Then("Select 14a, 14b,14c ,14d from the seats")
	public void select_14a_14b_14c_14d_from_the_seats() {
		
			}

	@Then("Proceed to Payment")
	public void proceed_to_payment() {
		lwp.clickProceddBtn(driver);
		
	}

	@Then("Capture all details from the confirm booking")
	public void capture_all_details_from_the_confirm_booking() {
		
	}
}
