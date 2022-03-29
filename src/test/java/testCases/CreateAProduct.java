package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.HashMap;
public class CreateAProduct {
	
	@Test
	public void createAProduct() {
		
		HashMap<String,String> payload = new HashMap<String,String>();
		payload.put("name", "Iphone 15 Pro Max");
		payload.put("price", "2000");
		payload.put("description", "steve job made it himself");
		payload.put("category_id", "2");
		
		Response response = 
		
	given()
		.baseUri("https://techfios.com/api-prod/api/product")
		.headers("Content-Type","application/json; charset=UTF-8")
		.body(new File("src\\main\\java\\data\\CreatePayLoad.json")).
//		.body(payload).
//		.auth().preemptive().basic("username", "password").
	when()
		.post("/create.php").
	then()
		.extract().response();
		int actualStatusCode =response.getStatusCode();
		System.out.println("actual status code:" + actualStatusCode);
		Assert.assertEquals(actualStatusCode, 201);
		
		
		String actualHeader=response.getHeader("Content-Type");
		System.out.println("actual header:" + actualHeader);
		Assert.assertEquals(actualHeader, "application/json; charset=UTF-8");
		
		String responseBody =response.getBody().asString();
		System.out.println("response body is :"+ responseBody);
		
		JsonPath jp = new JsonPath(responseBody);
		
		
		String productMessage =jp.get("message");
		System.out.println("product id is: "+productMessage);
		Assert.assertEquals(productMessage, "Product was created.");
		
	}
	
	
}
