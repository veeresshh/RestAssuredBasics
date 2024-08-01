import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import Files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class FileUpload {

	public static void main(String[] args) throws IOException {
		
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String Response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\VEERESH\\Downloads\\new 2.json"))))
		.when().post("maps/api/place/add/json")
		.then().assertThat().log().all().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
	
		System.out.println(Response);
		
		JsonPath JS = ReusableMethods.RawtoJson(Response);
		String PlaceID = JS.getString("place_id");
		System.out.println(PlaceID);

	}

}

