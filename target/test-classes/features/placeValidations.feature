Feature: Validating Place APIs

@AddPlace
Scenario Outline: Verify if place is successfully being added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then The API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And using "GetPlaceAPI" verify created place_ID mapped to this "<name>"
	
Examples:
	|name			|language			|address				|
	|Apple House	|English-US			|225 Washington Blvd	|
	|Banana House	|French				|123 Cross Rd			|

@DeletePlace
Scenario: Verify if DeletePlaceAPI is working
	Given DeletePlace payload
	When user calls "DeletePlaceAPI" with "POST" http request
	Then The API call got success with status code 200 
	And "status" in response body is "OK"
	
	# - use '#' hash to comment into .feature file
	
	#here, 'When', 'Then', 'And' statement is exactly same as above scenario (except parameters)
	#so, when u run the TestRunner, Only StepDefinition will be constructed and shown for 'Given' in console
	#which need to be added into stepDefinition
	
	# '@AddPlace', '@DeletePlace' are the tags/names given to each scenario. It would be helpful if you want 
# to execute specific scenarios only. you can mention these tags into TestRunner to execute them.
# tagging is optional