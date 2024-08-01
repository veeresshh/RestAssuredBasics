package Files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	
	public static JsonPath RawtoJson (String Responses)
	
	{
		JsonPath JS = new JsonPath(Responses);
		return JS;
	}

}
