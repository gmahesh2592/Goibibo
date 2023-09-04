package com.opsera.pages;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import com.opsera.commonfunctions.CommonFunctions;
import com.opsera.config.ConfigurationManager;


public class CreatePost {
	CommonFunctions cf = new CommonFunctions();
	ConfigurationManager cm = new ConfigurationManager();

	public static void validateGetRequestValidateSchema(String apiUrl) throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(apiUrl);
        HttpResponse response = httpClient.execute(httpGet);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        HttpEntity entity = response.getEntity();
        Assert.assertTrue(entity.getContentType().getValue().contains("application/json"));
        String responseBody = EntityUtils.toString(entity);
        JSONObject jsonResponse = new JSONObject(responseBody);
        Assert.assertEquals(jsonResponse.getInt("userId"),1);
        Assert.assertEquals(jsonResponse.getInt("id"), 1);
        Assert.assertEquals(jsonResponse.getString("title"), "delectus aut autem");
        Assert.assertFalse(jsonResponse.getBoolean("completed"));
}
	public static void validatePostRequest(String apiUrl, String requestBody) throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(apiUrl);
        httpPost.setEntity(new StringEntity(requestBody, "application/json", "UTF-8"));
        HttpResponse response = httpClient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 201);
        if (statusCode == 201) {
          
            validatePostResponse(response);
        } else {
            handleNegativeResponse(statusCode);
        }
    }
	public static void validatePostResponse(HttpResponse response) throws Exception {
        HttpEntity entity = response.getEntity();
        
        Assert.assertTrue(entity.getContentType().getValue().contains("application/json"));
        
        String responseBody = EntityUtils.toString(entity);
        JSONObject jsonResponse = new JSONObject(responseBody);
        
        // Validate specific fields in the JSON response.
        int id = jsonResponse.getInt("id");
        Assert.assertEquals(id, 101);
        Assert.assertNotEquals(id, 103);
    }
	
	  public static void handleNegativeResponse(int statusCode) {
	        switch (statusCode) {
	            case 400:
	                // Handle a 400 status code (Bad Request).
	                Assert.assertEquals(statusCode, 400);
	                break;
	            case 409:
	                // Handle a 409 status code (Conflict).
	                Assert.assertEquals(statusCode, 409);
	                break;
	            case 415:
	                // Handle a 415 status code (Unsupported Media Type).
	                Assert.assertEquals(statusCode, 415);
	                break;
	            default:
	                // Handle unexpected status codes by failing the test.
	                Assert.fail("Unexpected status code: " + statusCode);
	                break;
	        }
	    }
	  public static void main(String[] args) throws Exception {
	        String getApiUrl = "https://jsonplaceholder.typicode.com/todos/1";
	        String postApiUrl = "https://jsonplaceholder.typicode.com/posts";
	        String postRequestBody = "{ \"id\": \"103\" }";
	        validateGetRequestValidateSchema(getApiUrl);
	        validatePostRequest(postApiUrl, postRequestBody);
	    }

}