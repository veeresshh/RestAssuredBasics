package GraphQL;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;


public class GraphQL {
	
	public static void main(String[] args) {

		
//Query
	String Response = 
	given().log().all().header("content-Type", "application/json")
	.body("{\"query\":\"query($characterId: Int!,$episodeId: Int! )\\n\\n{\\n  character(characterId:$characterId) \\n  {\\n    name\\n    gender\\n    status\\n    id\\n  }\\n  \\n  location(locationId:11875)\\n  {    \\n    name\\n    dimension\\n  }\\n  \\n  episode(episodeId:$episodeId) \\n  {\\n    name\\n    air_date\\n    episode\\n  }\\n  \\n  characters(filters: { name:\\\"Rahul\\\"})  \\n  {\\n    info\\n    {\\n      count\\n    }\\n    \\n    result\\n    {\\n      name\\n      type\\n    }\\n  }\\n  \\n  episodes(filters:{episode:\\\"hulu\\\"})\\n  {\\n    result\\n    {\\n      id\\n      name\\n      air_date\\n      episode\\n    }\\n  }\\n}\",\"variables\":{\"characterId\":7708,\"episodeId\":9264}}")
	.when().post("https://rahulshettyacademy.com/gq/graphql")
	.then().extract().response().asString();
	
	
	System.out.println(Response);
	
	JsonPath js = new JsonPath(Response);
	String CharacterName = js.getString("data.character.name");
	Assert.assertEquals(CharacterName, "Veeresh");
			
//Mutation
	String MutationResponse = 
	given().log().all().header("content-Type", "application/json")
	.body("{\"query\":\"mutation($locationName:String!, $characterName:String!, $episodeName:String!)\\n{\\n  createLocation(location : {name:$locationName, type:\\\"South\\\", dimension:\\\"234\\\"})\\n  {\\n    id\\n  }\\n  createCharacter(character: {name:$characterName, type:\\\"Macho\\\", status:\\\"Alive\\\", species:\\\"fantasy\\\", gender:\\\"Male\\\", image:\\\"png\\\", locationId:11869, originId:11869})\\n  {\\n    id\\n  }\\n  createEpisode(episode:{name:$episodeName, air_date:\\\"29-04-1993\\\", episode:\\\"Prime\\\"})\\n  {\\n    id\\n  }\\n  \\n  deleteLocations(locationIds:[11872,11873])\\n  {\\n    locationsDeleted\\n  }\\n}\",\"variables\":{\"locationName\":\"India\",\"characterName\":\"Veeresh\",\"episodeName\":\"KGF\"}}")
	.when().post("https://rahulshettyacademy.com/gq/graphql")
	.then().extract().response().asString();
	
	
	System.out.println(MutationResponse);
	
	}

}
