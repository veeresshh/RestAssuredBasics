package OUTH2;

import static io.restassured.RestAssured.given;


import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;


public class OUTH2 {

	public static void main(String[] args) throws InterruptedException {

		String URL = "https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2FvAHBQUZU6o4WJ719NrGBzSELBFVBI9XbxvOtYpmYpeV47bFVExkaxWaF_XR14PHtTZf7ILSEeamywJKwo_BYs9M&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&session_state=0c32992f0d47e93d273922018ade42d1072b9d1f..a35c&prompt=none#";

		String PartialCode = URL.split("code=")[1];

		String Code = PartialCode.split("&scope")[0];

		System.out.println(Code);

		String AccessTokenResponse =

				        given().urlEncodingEnabled(false)

						.queryParams("code", Code)

						.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")

						.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")

						.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")

						.queryParams("grant_type", "authorization_code")

						.queryParams("state", "verifyfjdss")

						
						
						.when().log().all()

						.post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		

		JsonPath jsonPath = new JsonPath(AccessTokenResponse);

		String AccessToken = jsonPath.getString("access_token");

		System.out.println(AccessToken);
		
		
		
		

		String CodeResponse =

				         given().contentType("application/json")

						.queryParams("access_token", AccessToken).expect().defaultParser(Parser.JSON)
						
						

						.when().get("https://rahulshettyacademy.com/getCourse.php").asString();
		

		System.out.println(CodeResponse);

	}

}