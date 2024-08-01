package POJO;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class POJO {

	public static void main(String[] args) {
		
		String[] courseTitles = {"Selenium Webdriver Java","Cypress","Protractor"};
	
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
       
    
    GetCourse CourseDetails=    
        		     		
        	given()
                .queryParams("access_token", AccessToken)
                
            .when().log().all()
            .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);

 
             System.out.println(CourseDetails.getExpertise());
             System.out.println(CourseDetails.getInstructor());
             System.out.println(CourseDetails.getLinkedIn());
             System.out.println(CourseDetails.getServices());
             System.out.println(CourseDetails.getUrl());
             System.out.println(CourseDetails.getClass());
             
//Nested Details    
             
             System.out.println(CourseDetails.getCourses().getApi().get(1).getCourseTitle());
             
//Search Based on Title 
             
             List<Api> ApiCourses = CourseDetails.getCourses().getApi();
             
             for(int i=0;i<ApiCourses.size();i++)
            	 
         	{
         		if(ApiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
         				
         		{
         			System.out.println(ApiCourses.get(i).getPrice());
         		}
         		
         	}
             
//Get the course names of WebAutomation
             
             
 			ArrayList<String> a= new ArrayList<String>();
 			
 			List<WebAutomation> w=CourseDetails.getCourses().getWebAutomation();
 			
 			for(int j=0;j<w.size();j++)
 				
 			{
 				a.add(w.get(j).getCourseTitle()); 
 			}
 			
 			//Comparing Array with Array List by Converting Array to Array List
 			
 			List<String> ExpectedList=	Arrays.asList(courseTitles);
 			
 			Assert.assertTrue(a.equals(ExpectedList));
 				            

     }

}