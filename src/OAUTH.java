import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;



public class OAUTH {

	public static void main(String[] args) {
	
		String Response =

             given()
                .formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .formParams("grant_type", "client_credentials")
                .formParams("scope", "trust")

             .when().log().all()

             .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		
        System.out.println(Response);
               

    JsonPath jsonPath = new JsonPath(Response);

    String AccessToken = jsonPath.getString("access_token");

    System.out.println(AccessToken);
       
    
        String SecondResponse=    
        		
        		
        	given()
                .queryParams("access_token", AccessToken)
                
            .when().log().all()
            .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();

       
        System.out.println(SecondResponse);

}



}