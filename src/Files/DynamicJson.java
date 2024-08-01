package Files;
import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	
	@Test(dataProvider="BooksData")
	public void AddBookData(String isbn, String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		
		String Response = given().log().all().header("Content-Type","application/json")
		.body(PayLoad.AddBook(isbn, aisle))
		
		.when().post("/Library/Addbook.php")
		
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath JS = ReusableMethods.RawtoJson(Response);
		String ID = JS.get("ID");
		
		System.out.println("ID Of Book is " + ID);
	}
	
	@DataProvider(name="BooksData")
	public Object[][] GetData()
	{
		// Array - Collection of Elements
		// Multi Dimensional Array - Collection of Arrays
		 return new Object[][] {{"ABCD","1234"},{"EFGH","5678"},{"IJKL","9101112"}};
	}

}
