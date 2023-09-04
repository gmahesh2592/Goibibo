package com.opsera.stepDef;


import com.opsera.commonfunctions.CommonFunctions;
import com.opsera.config.ConfigurationManager;
import com.opsera.config.TestFileReader;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class HookAPI {
	CommonFunctions cf = new CommonFunctions();
	ConfigurationManager cm = new ConfigurationManager();
	private String requestBody;
	private String endpoint="/api/users/login";
	private Response response;
	public static String authToken;
	public static String refreshToken;

	/*
	 * @Before public void ToGetAccessToken() throws Exception {
	 * 
	 * RestAssured.useRelaxedHTTPSValidation(); RestAssured.baseURI =
	 * cm.getProperty("BaseURI"); String filepath =
	 * "src/test/resources/UAMAPI_TestData/LoginwithPassword_API_TEST01.txt";
	 * this.requestBody = TestFileReader.readFileAsString(filepath); response =
	 * RestAssured.given().contentType(ContentType.JSON).body(requestBody).post(
	 * endpoint); JsonPath jsonpath = response.jsonPath(); authToken =
	 * jsonpath.getString("auth_tokens.access_token"); refreshToken =
	 * jsonpath.getString("auth_tokens.refresh_token"); }
	 */
}

	

