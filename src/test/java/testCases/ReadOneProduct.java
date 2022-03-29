package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
public class ReadOneProduct {
	
	@Test
	public void readOneProduct() {
		

		Response response = 
		
	given()
		.baseUri("https://techfios.com/api-prod/api/product")
		.headers("Content-Type","application/json")
		.queryParam("id", "3923")
		.auth().preemptive().basic("username", "password").
	when()
		.get("/read_one.php").
	then()
		.extract().response();
		
		int actualStatusCode =response.getStatusCode();
		System.out.println("actual status code:" + actualStatusCode);
		Assert.assertEquals(actualStatusCode, 404);
		
		
		String actualHeader=response.getHeader("Content-Type");
		System.out.println("actual header:" + actualHeader);
		Assert.assertEquals(actualHeader, "application/json");
		
		String responseBody =response.getBody().asString();
		System.out.println("response body is :"+ responseBody);
		
		JsonPath jp = new JsonPath(responseBody);
		
		
		String productId =jp.get("id");
		System.out.println("product id is: "+productId);
		Assert.assertEquals(productId, "3937");
		
		String productPrice =jp.get("price");
		System.out.println("product price is: "+productPrice);
		Assert.assertEquals(productPrice, "399");
	}
	
	
}
