package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

/* This is Cucumber Hooks concept. when you create Hooks class, it set pre-conditions to the
 * test scenarios. Here, if we have to run just DeletePlace test scenario, then we give that test
 * scenario name into tag name in TestRunner. However, to run DeletePlace test successfully, we have
 * to run AddPlace test scenario. so, here in this hook, we will set to run AddPlace test scenario
 * as a pre-condition to get execute @DeletePlace independently.
 * Note that, if TagName is not mentioned into TestRunner, then Hooks class will not execute and all test
 * scenarios gets executed as it is.
 */
public class Hooks {
	
	/* @Before annotation is used to execute a piece of code before a test scenario.
	 * here, we have to use runSceanrio() before @DeletePlace scenario gets executed.
	 */
	@Before("@DeletePlace")
	public void runScenario() throws IOException
	{
		/* Here write a code which will give you place id (i.e. execute AddPlace scenario).
		 * And execute this code only if Place_ID is null; but why?
		 * because if the all the test scenarios then then AddPlace will get executed twice
		 * so, execute this piece only if AddPlace not get executed prior to DeletePlace.
		 * that's why we are checking if Place_ID is null.
		 */
		
		if(stepDefinition.place_id==null) //here Place_id variable has been accessed using class name b'coz it's static variable
		{
			//add Given/When/Then methods from AddPlace scenarios.
			//All these methods are already been implemented in StepDefinition.java
			stepDefinition sd = new stepDefinition();
			sd.add_Place_Payload_with("Gopal P", "Marathi", "Datta Mandir");
			sd.user_calls_with_POST_http_request("AddPlaceAPI", "POST");
			sd.using_verify_created_place_ID_mapped_to_this("GetPlaceAPI", "Gopal P");
		}
	}
}
