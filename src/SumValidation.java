import org.testng.Assert;
import org.testng.annotations.Test;

import Files.PayLoad;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
		@Test
		public void SumofCourses()
		{
			int Sum =0;
			
			
			JsonPath JS = new JsonPath(PayLoad.CoursePrice());
			int count = JS.getInt("courses.size()");
			
			for(int i=0 ; i<count; i++)
			{
				int priceofCourse = JS.getInt("courses["+i+"].price");
				int Copies = JS.getInt("courses["+i+"].copies");
				
				int amount = priceofCourse*Copies ;
				
				System.out.println(amount);
				
				Sum = Sum+amount; //Imp
				
			}
			
			System.out.println("Total Price is " + Sum);
			int PurchaseAmount = JS.getInt("dashboard.purchaseAmount");
			
			Assert.assertEquals(Sum, PurchaseAmount);
		}

	

}
