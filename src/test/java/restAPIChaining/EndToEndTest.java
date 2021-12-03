package restAPIChaining;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEndTest {
	
	@Test
	public void test1() {
		RestAssured.baseURI = "http://localhost:7000";
		//===================Post Call=====================
		RequestSpecification Postrequest = RestAssured.given();
		
		Map<String,Object> PostBody = new HashMap<String, Object>();
		PostBody.put("name", "Ganesh");
		PostBody.put("salary", "6500");
		Response Postresponse = Postrequest.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(PostBody).post("/employees/create");

		String ResponseBody =Postresponse.body().asString();
		System.out.println(ResponseBody);
		
		
		JsonPath jpath = Postresponse.jsonPath();
		String ResponseID = jpath.get("id").toString();


		//=====================Put Call=======================
		
		RequestSpecification Putrequest = RestAssured.given();

		Map<String,Object> PutBody = new HashMap<String, Object>();
		PutBody.put("name", "Sandeep");
		PutBody.put("salary", "7500");
		Response Putresponse = Putrequest.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(PutBody).put("/employees/"+ ResponseID);

		String PutResponseBody =Putresponse.body().asString();
		System.out.println(PutResponseBody);
		
		
		//=======================Delete Call========================
		
		RequestSpecification Deleterequest = RestAssured.given();

		Response Deleteresponse = Deleterequest.delete("/employees/" +ResponseID);
		
		int ActResponse = Deleteresponse.getStatusCode();
		int expectedResponse = 200;

		AssertJUnit.assertEquals(ActResponse, expectedResponse);
		
		
		//======================Get Call====================
		

		RequestSpecification Getrequest = RestAssured.given();
		Response Getresponse = Getrequest.get("/employees/"+ ResponseID);
		
		int ActGetResponse = Getresponse.getStatusCode();
		int expectedGetResponse = 404;

		AssertJUnit.assertEquals(ActGetResponse, expectedGetResponse);
	}

}
