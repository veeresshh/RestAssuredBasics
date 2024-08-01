package Excel;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class ExcelDriven {

	@Test
	public void addBook() throws IOException {
		dataDrivenExcel d = new dataDrivenExcel();
		ArrayList<String> data = d.getdata("RestAddbook", "RestAssured");

		HashMap<String, Object> map = new HashMap<>();
		map.put("name", data.get(1));
		map.put("isbn", data.get(2));
		map.put("aisle", data.get(3));
		map.put("author", data.get(4));

		/*
		 * HashMap<String, Object> map2 = new HashMap<>(); map.put("lat", "12");
		 * map.put("lng", "34"); map.put("location", map2);
		 */

		RestAssured.baseURI = "http://216.10.245.166";
		
		
		String Responses = given().header("Content-Type", "application/json").body(map).when()
				.post("/Library/Addbook.php").then().assertThat().statusCode(200).extract().response().asString();

		JsonPath JS = ReusableMethodsExcel.RawtoJson(Responses);
		String ID = JS.get("ID");
		System.out.println(ID);

	}

	public static String GenerateStringFromResource(String path) throws IOException {

		return new String(Files.readAllBytes(Paths.get(path)));

	}
}
