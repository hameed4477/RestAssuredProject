package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;

public class DeleteAProduct {
	
	@Test(priority = 1)
	public void deleteAProduct() {
		

		Response response = 
		
	given()
		.baseUri("https://techfios.com/api-prod/api/product")
		.headers("Content-Type","application/json; charset=UTF-8")
		.body(new File("src\\main\\java\\data\\DeletePayLoad.json")).
//		.body(payload).
//		.auth().preemptive().basic("username", "password").
	when()
		.delete("/delete.php").
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
		
		JsonPath jp = new JsonPath(responseBody);
		
		
		String productMessage =jp.get("message");
		System.out.println("product id is: "+productMessage);
		Assert.assertEquals(productMessage, "Product was deleted.");
		
	}
	@Test(priority =2)
	public void readOneProduct() {
		

		Response response = 
		
	given()
		.baseUri("https://techfios.com/api-prod/api/product")
		.headers("Content-Type","application/json")
		.queryParam("id", "3935")
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
		
		
		String productMessage =jp.get("message");
		System.out.println("product Message is: "+productMessage);
		Assert.assertEquals(productMessage, "Product does not exist.");
		
		
	}
	
	
}
