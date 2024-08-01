import Files.PayLoad;
import io.restassured.path.json.JsonPath;

public class ComplexJson {

	public static void main(String[] args) {
		
		JsonPath JS = new JsonPath(PayLoad.CoursePrice());
		
// Total Number of Courses
		
		// .size is used because courses is an array ([ ] is used)
		
		int count = JS.getInt("courses.size()");
		
		System.out.println(count);
		
// Purchase Amount
		
		int purchaseamount = JS.getInt("dashboard.purchaseAmount");
		
		System.out.println(purchaseamount);
		
// Title of First Course
		
		String titleoffirstcourse = JS.get("courses[0].title");
		
		System.out.println(titleoffirstcourse);

// All Courses and Respective Prices
		
		for(int i=0; i<count; i++)
		{
			String titleofCourse = JS.get("courses["+i+"].title"); // Imp			
			int priceofCourse = JS.getInt("courses["+i+"].price");
			
      // Price Can also be printed Directly Without Declaring int- System.out.println(JS.getInt("courses["+i+"].price").toSting);
			
			System.out.println(titleofCourse);
			System.out.println(priceofCourse);
		}		
//No of Courses Sold by RPA Course
		
		for(int k=0; k<count; k++)
		{
			String CourseTitle = JS.get("courses["+k+"].title");
			
			if(CourseTitle.equalsIgnoreCase("RPA"))
			{
				System.out.println(JS.get("courses["+k+"].copies").toString());
				break;
			}
			
					
		}
	}
	
}
