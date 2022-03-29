package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;

public class UpdateAProduct { 
	SoftAssert softAssert = new SoftAssert();
	// softAssert is used to run the rest of the code even if it fails at first assertion
	// once the whole code is run then, it will fail.
	//can not use softAssert with Junit
	// 
	@Test(priority = 1)
	public void updateAProduct() {
		

		Response response = 
		
	given()
		.baseUri("https://techfios.com/api-prod/api/product")
		.headers("Content-Type","application/json; charset=UTF-8")
		.body(new File("src\\main\\java\\data\\UpdatePayLoad.json")).
//		.body(payload).
//		.auth().preemptive().basic("username", "password").
	when()
		.put("/update.php").
	then()
		.extract().response();
		int actualStatusCode =response.getStatusCode();
		System.out.println("actual status code:" + actualStatusCode);
		softAssert.assertEquals(actualStatusCode, 200,"status code does not match");
		
		
		String actualHeader=response.getHeader("Content-Type");
		System.out.println("actual header:" + actualHeader);
		softAssert.assertEquals(actualHeader, "application/json; charset=UTF-8", "header does not match");
		
		String responseBody =response.getBody().asString();
		System.out.println("response body is :"+ responseBody);
		
		JsonPath jp = new JsonPath(responseBody);
		
		
		String productMessage =jp.get("message");
		System.out.println("product id is: "+productMessage);
		softAssert.assertEquals(productMessage, "Product was updated.", "messages do not match");
		softAssert.assertAll();
		
	}
//	@Test(priority =2)
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
		Assert.assertEquals(actualStatusCode, 200);
		
		
		String actualHeader=response.getHeader("Content-Type");
		System.out.println("actual header:" + actualHeader);
		Assert.assertEquals(actualHeader, "application/json");
		
		String responseBody =response.getBody().asString();
		System.out.println("response body is :"+ responseBody);
		
		JsonPath jp = new JsonPath(responseBody);
		
		
		String productId =jp.get("id");
		System.out.println("product id is: "+productId);
		Assert.assertEquals(productId, "3923");
		
		String productPrice =jp.get("price");
		System.out.println("product price is: "+productPrice);
		Assert.assertEquals(productPrice, "2500");
	}
	
	
}
