package com.opsera.stepDef;

import java.util.HashMap;
import java.util.Map;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.opsera.config.ConfigurationManager;
import com.opsera.config.DriverFactory;
import com.opsera.config.TestFileReader;
 



public class ValidateGetPostRequest {
	private Response response;
	private Map<String, String> headers = new HashMap<>();
	private Map<String, String> queryParameters = new HashMap<>();
	private Map<String, String> pathParameters = new HashMap<>();
	private String requestBody;
	ConfigurationManager configurationManager = new ConfigurationManager();
	private String TestDataFolder = "src/test/resources/UAMAPI_TestData";
	WebDriver driver = DriverFactory.getDriver();
	
	@Given("^I have the base URL$")
	public void setBaseUrl() {
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = configurationManager.getProperty("BaseURI");
	}

	@And("I have the following headers:$")
	public void setHeaders(Map<String, String> headermap) {
		headers.putAll(headermap);
	}

	@And("I have the following queryparameter:$")
	public void setQueryparams(Map<String, String> queryparameters) {
		queryParameters.putAll(queryparameters);
	}
	
	@And("^I have the following pathparameter:$")
	public void setPathparams(Map<String, String> pathparameters) {
		pathParameters.putAll(pathparameters);
	}

	@And("^I have the following JSON request body \"(.*)\"$")
	public void setRequestBody(String filename) {
		String filepath = TestDataFolder + "/" + filename;

		this.requestBody = TestFileReader.readFileAsString(filepath);

	}
	
	@And("^I have the Json body as null$")
	public void setRequestBodyAsNull() {
		this.requestBody="{}";
	}

	@When("^I send a POST request to \"(.*)\"$")
	public void sendPOSTRequest(String endpoint) {

		response = RestAssured.given().header("Authorization", "Bearer " + HookAPI.authToken).headers(headers)
				.queryParams(queryParameters).contentType(ContentType.JSON).body(requestBody).post(endpoint);
		response.prettyPrint();

	}
	
	@When("^I send a POST request with pathparameters \"(.*)\"$")
	public void sendPOSTRequestwithPathParam(String endpoint) {

		response = RestAssured.given().header("Authorization", "Bearer " + HookAPI.authToken).headers(headers)
				.queryParams(queryParameters).pathParams(pathParameters).contentType(ContentType.JSON).body(requestBody).post(endpoint);
		response.prettyPrint();

	}

	@When("^I send a POST request to \"(.*)\" using refresh token$")
	public void sendPOSTRequestwithToken(String endpoint) {

		response = RestAssured.given().headers(headers).header("Authorization", "Bearer " + HookAPI.refreshToken)
				.contentType(ContentType.JSON).body(requestBody).post(endpoint);
		response.prettyPrint();

	}
	
	@When("^I send a POST request to \"(.*)\" without authorization$")
	public void sendPOSTRequestwithoutAuthorization(String endpoint) {
		response = RestAssured.given().headers(headers)
				.contentType(ContentType.JSON).body(requestBody).post(endpoint);
	}

	@When("I send a GET request to \"(.*)\"$")
	public void sendGETRequest(String endpoint) {

		response = RestAssured.given().header("Authorization", "Bearer " + HookAPI.authToken).headers(headers)
				.queryParams(queryParameters).get(endpoint);
		response.prettyPrint();
	}

	@Then("^the response status code should be (\\d+)$")
	public void verifyStatusCodefromResponse(int value) {
		int actualStatusCode = response.getStatusCode();
		Assert.assertEquals(value, actualStatusCode);
	}

	@Then("^the response should contain \"(.*)\"$")
	public void verifyResponseContains(String expectedValue) {
		String responseBody = response.getBody().asString();
		Assert.assertTrue(responseBody.contains(expectedValue));
	}

	@And("^the response should contain$")
	public void verifyresponsewithexpectedvalues(Map<String, String> expectd) {
		for (Map.Entry<String, String> entry : expectd.entrySet()) {
			String key = entry.getKey();
			String expectedValue = entry.getValue();
			String actualValue = response.jsonPath().getString(key);
			Assert.assertEquals(expectedValue, actualValue);
			
		}

	}
 
	@And("the response should have (\\d+) number of \"(.*)\" elements$")
	public void verifycountofObjects(int count, String elementType) {
		
		int actualvalue = response.jsonPath().getList(elementType).size();
		Assert.assertEquals(count, actualvalue);
	}
	
	
}
