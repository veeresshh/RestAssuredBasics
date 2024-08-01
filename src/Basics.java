import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.PayLoad;
import Files.ReusableMethods;

public class Basics {

	public static void main(String[] args) {
		
		//Given - All Input Details
		//When - Submit the API
		//Then - Validate the Response
		
		// POST
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String Response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(PayLoad.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().assertThat().log().all().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
	
		System.out.println(Response);
		
		JsonPath JS = ReusableMethods.RawtoJson(Response);
		String PlaceID = JS.getString("place_id");
		System.out.println(PlaceID);
		
		
		// PUT
		
		String NewAddress = "Summer Walk Africa";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+PlaceID+"\",\r\n"
				+ "\"address\":\""+NewAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("/maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

		
		//GET
		
		String GetPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", PlaceID)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		
		JsonPath PS = new JsonPath(GetPlaceResponse);
		String ActualAddress = PS.getString("address");
		System.out.println(ActualAddress);
		
		Assert.assertEquals(ActualAddress, NewAddress);
		

	}

}
