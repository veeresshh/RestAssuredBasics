import static io.restassured.RestAssured.given;

import java.io.File;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
public class JiraLogUpload {

	public static void main(String[] args) {

	RestAssured.baseURI="https://veeresshh.atlassian.net/";

	String createIssueResponse = 
			
			given()
			.header("Content-Type", "application/json")
			.header("Authorization","Basic dmVlcmVzaDAyNzBAZ21haWwuY29tOkFUQVRUM3hGZkdGMGhTcjRLQUIxdXNQRUZVSHNXMTBlMUJPdnZ2YTAzOFItWTZ5MG9XeW55RmdZMW5SdlVSb20zanU4NGd4eGJGTGdWWGk4S0RJUWdNWkQ5cUdacDhpM0lxWXZneWpKa1cyT1FxRWZTbXlVMHR4M0Y3NHF6T0UxZ3BxZ0M1eTdVX25NdllGYmlSa0loTUFyd1lHb2pPaDRYZEJtVFN3Zmx4SkUwOGxUX0NwVGdhZz0wQzRFOTQ4NQ==")
			.body("{\n" 
			        + "    \"fields\": {\n" 		      
					+ "        \"project\":\n" 
			        + "       {\n"
					+ "          \"key\": \"SCRUM\"\n"
			        + "       },\n"
					+ "       \"summary\": \"Page Not Loading\",\n"
					+ "       \"issuetype\": {\n"
					+ "          \"name\": \"Bug\"\n"
					+ "       }\n"
					+ "   }\n"
					+ "}")
			.log().all()
			
			.post("rest/api/3/issue")
			.then().log().all().assertThat().statusCode(201)
			.contentType("application/json").extract().response().asString();
	
	
	JsonPath js = new JsonPath(createIssueResponse);
	String issueId = js.getString("id");System.out.println(issueId);

	
	        given().pathParam("key", issueId)
	        .header("X-Atlassian-Token","no-check")			
	        .header("Authorization","Basic dmVlcmVzaDAyNzBAZ21haWwuY29tOkFUQVRUM3hGZkdGMGhTcjRLQUIxdXNQRUZVSHNXMTBlMUJPdnZ2YTAzOFItWTZ5MG9XeW55RmdZMW5SdlVSb20zanU4NGd4eGJGTGdWWGk4S0RJUWdNWkQ5cUdacDhpM0lxWXZneWpKa1cyT1FxRWZTbXlVMHR4M0Y3NHF6T0UxZ3BxZ0M1eTdVX25NdllGYmlSa0loTUFyd1lHb2pPaDRYZEJtVFN3Zmx4SkUwOGxUX0NwVGdhZz0wQzRFOTQ4NQ==")        
	        .multiPart("file",new File("C:\\Users\\VEERESH\\Downloads\\WhatsApp Image 2023-12-28 at 11.15.54 AM (1).jpeg")).log().all()
	        
	        .post("rest/api/3/issue/{key}/attachments") //Imp Step - Observe Key
	        .then().log().all().assertThat().statusCode(200);			
		 	 		 		 		 		 							}
}
