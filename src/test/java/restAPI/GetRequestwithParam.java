package restAPI;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequestwithParam {
	
	@Test
	public void GetCall() {
		RestAssured.baseURI="http://localhost:7000";
		
		RequestSpecification request = RestAssured.given();
		
		Response response = request.param("id", "1").get("/employees");
		String ResponseBody = response.body().asString();
		System.out.println(ResponseBody);
		
		//===================verification status code=======================
		
		int ActResponse = response.getStatusCode();
		int expectedResponse = 200;
		
		AssertJUnit.assertEquals(ActResponse, expectedResponse);
		
		//====================Verify Headers=================
		System.out.println(response.getHeaders());
		
		
		//=====================Particular Header==========
		String ActualHeader = response.getHeader("Content-Type");
		String ExpHeader = "application/json; charset=utf-8";
		
		AssertJUnit.assertEquals(ActualHeader, ExpHeader);
		
		
		//==========================Verify Response Body===============
		AssertJUnit.assertTrue(ResponseBody.contains("Pankaj"));
		
		//====================Verify Response on particular field================
		
		JsonPath jpath = response.jsonPath();
		
		List <String> Names = jpath.get("name");
		
		String ActName = Names.get(0);
		String ExpName = "Pankaj";
		AssertJUnit.assertEquals(ActName, ExpName);
		
	}

}
