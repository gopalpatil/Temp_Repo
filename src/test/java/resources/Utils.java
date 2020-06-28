package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	public static RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	PrintStream log;
	
	public RequestSpecification reqSpecification() throws IOException
	{  
	   if(reqSpec == null) //to avoid the log file getting created fresh every time
	   {
		   log = new PrintStream(new FileOutputStream("logging.txt"));
		    
		    reqSpec = 
		    		new RequestSpecBuilder()
		    		.setBaseUri(getConfigValues("baseURL"))
		    		.setContentType(ContentType.JSON)
		    		.addQueryParam("key",getConfigValues("key"))
		    		.addFilter(RequestLoggingFilter.logRequestTo(log)) //to log request details
		    		.addFilter(ResponseLoggingFilter.logResponseTo(log)) //to log response details
		    		.build();
		    
		    return reqSpec;
	   }
	   return reqSpec;
	}
	
	public ResponseSpecification resSpecification()
	{
		resSpec = 
	    		new ResponseSpecBuilder()
	    		.expectStatusCode(200)
	    		.expectContentType(ContentType.JSON)
	    		.build();
		
		return resSpec;
	}
	
	public String getConfigValues(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = 
				new FileInputStream(
						"C:\\GopalP\\SeleniumProjects\\API_Cucumber_Framework1\\"
						+ "src\\test\\java\\resources\\config.properties");
		prop.load(fis);
		String keyValue = prop.getProperty(key);
		return keyValue;
	}
	
	public String getJSONPath(Response response, String key)
	{
		String apiResponse = response.asString();
		JsonPath js = new JsonPath(apiResponse);
		return js.get(key).toString();
	}
}
