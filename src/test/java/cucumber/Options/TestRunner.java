package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(features="src/test/java/features"
					,glue= {"stepDefinitions"}
					,tags= {"@DeletePlace"}    //tags is optional
					,plugin="json:target/jsonReports/cucumber-report.json"
					,strict=true
				) 

/* this is TestRunner class body; it can be blank*/
public class TestRunner {

}

/*@CucumberOptions(features="src/test/java/features",
glue= {"stepDefinitions"})*/

/* here, 'tag' parameter (optional) is added to notify TestRunner that the just run the Test
 * scenario which has tag name '@AddPlace' in Feature file. It will search this tagname in all available
 * feature files and execute only those test scenarios which has '@AddPlace' tags.
 */
