package restApiBdd;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class DeleteRequest {
	@Test
	public void DeleteCall() {
		RestAssured.given().baseUri("http://localhost:7000")
		.when().delete("/employees/5")
		.then()
		.statusCode(200)
		.log().all(); //.body()
	}

}
