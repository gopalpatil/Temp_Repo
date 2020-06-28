package stepDefinitions;

import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.TestDataBuilder;
import resources.Utils;

public class stepDefinition extends Utils {
	
	RequestSpecification reqS;
	Response response;
	static String place_id; //here 'static' keyword is used because we are going to use place_id
	//variable from one scenario to another scenario. when we use 'static' keyword, the value assigned to
	//it once, can be used for all further test cases (in a execution). If 'static' keyword is not used
	//then all the variables here are going to reset and set to null for each scenario execution.
	
	TestDataBuilder testData = new TestDataBuilder();
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
	    
	    reqS = 
	    		given()
	    			.spec(reqSpecification())
	    			.body(testData.addPlacePayload(name, language, address));
 
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_POST_http_request(String resource, String httpMethod) {
		
		APIResources resourceAPI =  APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		if(httpMethod.equalsIgnoreCase("POST"))
		{
			response = reqS
					.when()
						//.post("/maps/api/place/add/json")
						.post(resourceAPI.getResource());
		}
		else if(httpMethod.equalsIgnoreCase("GET"))
		{
			response = reqS
					.when()
						.get(resourceAPI.getResource());
		}		
	}

	@Then("The API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
		
		assertEquals(response.getStatusCode(), 200);
	}
	

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyName, String expectedValue) {
		
		//assertEquals(js.get(keyValue).toString(), expectedValue);
		assertEquals(getJSONPath(response,keyName),expectedValue);
	}
	
	@Then("using {string} verify created place_ID mapped to this {string}")
	public void using_verify_created_place_ID_mapped_to_this(String resource, String expectedName) throws IOException {
	    
		place_id = getJSONPath(response, "place_id");
		reqS = 
				given()
				.spec(reqSpecification())
				.queryParam("place_id", place_id);
				//.queryParam("key", getConfigValues("key"));
		
		user_calls_with_POST_http_request(resource, "GET");
		//here we reused 'when' statement in feature files
		
		//now  get the name from response
		String actualName = getJSONPath(response,"name");
		assertEquals(actualName, expectedName);  
	}
	
	@Given("DeletePlace payload")
	public void deleteplace_payload() throws IOException {
		reqS = 
				given()
				.spec(reqSpecification())
				//.queryParam("place_id", place_id); --here we dont have to send Place_id in queryParam,
				//we have to send it in body
				.body(testData.deletePlacePayload(place_id));		 
	}
}
