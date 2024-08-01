package Excel;

import io.restassured.path.json.JsonPath;

public class ReusableMethodsExcel {
	
	public static JsonPath RawtoJson (String Responses)
	
	{
		JsonPath JS = new JsonPath(Responses);
		return JS;
	}

}
