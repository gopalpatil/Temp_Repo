package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuilder {
	
	public AddPlace addPlacePayload(String name, String language, String address)
	{
		AddPlace place = new AddPlace();
	    place.setAccuracy(50);
	    place.setAddress(address);
	    place.setName(name);
	    place.setPhone_number("(+91) 983 893 3937");
	    place.setWebsite("http://google.com");
	    place.setLanguage(language);
	   
	    List<String> placeTypes = new ArrayList<String>();
	    placeTypes.add("shoe park");
	    placeTypes.add("Shop");
	    
	    place.setTypes(placeTypes);
	    
	    Location loc = new Location();
	    loc.setLat(-38.383494);
	    loc.setLng(33.427362);
	    
	    place.setLocation(loc);
	    
	    return place;
	}
	
	public String deletePlacePayload(String placeID)
	{
		return "{\r\n    \"place_id\":\""+placeID+"\"\r\n}";
		//here json body is converted into Java readable string format
	}
}
