package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
public class ReadAllProducts {
	
	@Test
	public void readAllProducts() {
		
		Response response = 
		
	given()
		.baseUri("https://techfios.com/api-prod/api/product")
		.headers("Content-Type","application/json; charset=UTF-8").
	when()
		.get("/read.php").
	then()
		.extract().response();
		
		int actualStatusCode =response.getStatusCode();
		System.out.println("actual status code:" + actualStatusCode);
		Assert.assertEquals(actualStatusCode, 200);
		
		
		String actualHeader=response.getHeader("Content-Type");
		System.out.println("actual header:" + actualHeader);
		Assert.assertEquals(actualHeader, "application/json; charset=UTF-8");
		
		String responseBody =response.getBody().asString();
		System.out.println("response body is :"+ responseBody);
		
	}
	
	
}
