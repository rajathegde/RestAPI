package restApiBdd;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PutRequest {
	
	@Test
	public void PutCall() {
		
		Map<String,Object> PostBody = new HashMap<String, Object>();
		PostBody.put("name", "Sathish");
		PostBody.put("salary", "7500");
		
		RestAssured.given().baseUri("http://localhost:7000")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(PostBody)
		.when().put("/employees/5")
		.then()
		.statusCode(200)
		.log().all(); //.body()
	}

}
