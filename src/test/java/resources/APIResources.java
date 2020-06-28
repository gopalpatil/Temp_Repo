package resources;
//enum is a special class in java which contains collection of constants and methods
public enum APIResources {
	//these are treated as methods; don't need to return the values
	//each method is separated by comma and once set of method defining is completed,
	//give semicolon.
	//also NOTE THAT, this method name must be similar with the parameter values in 
	//the .feature file. e.g. we have used "AddPlaceAPI" into feature file; that's why
	//we have used same name here. We are doing this since we have to use enum method 
	//"valueOf()" while calling this method in StepDefinition class.
	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");

	private String resource;
	
	APIResources(String resource) 
	{
		//we have to create a constructor having same kind and number of parameters as
		//methods we created above. AddPlaceAPI, GetPlaceAPI, DeletePlaceAPI has string 
		//type of parameter and has only one parameter into each. so, we created this 
		//constructor having String type of parameter. .
		
		this.resource = resource;
	}
	
	public String getResource()
	{
		return resource;
	}
}
