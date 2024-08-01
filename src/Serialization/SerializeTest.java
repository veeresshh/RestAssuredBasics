package Serialization;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.List;

public class SerializeTest {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		AddPlace p = new AddPlace();
		
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName("Frontline house");
		
//Create an Object For List Class		
		List<String> Type = new ArrayList<String>();
		Type.add("shoe park");
		Type.add("shop");
		p.setTypes(Type);
		
//Creating Location Class Object		
		Location Loc = new Location();
		Loc.setLat(-38.383494);
		Loc.setLng(33.427362);		
		p.setLocation(Loc);
		
//See Parameter in Body Carefully		
		
		Response res = given().log().all().queryParam("key", "qaclick123").body(p).when()
				.post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response();


		String responseString = res.asString();
		System.out.println(responseString);

	}

}
