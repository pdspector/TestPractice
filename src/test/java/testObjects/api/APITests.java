package testObjects.api;

import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import apiObjects.GetCharactersResponse;
import resources.POJOCharacter;
import testObjects.BaseAPITest;

public class APITests extends BaseAPITest {
	
	private GetCharactersResponse characters;
	private String walterWhite = "{\"birthday\":\"09-07-1958\",\"img\":\"https://images.amcnetworks.com/amc.com/wp-content/uploads/2015/04/cast_bb_700x1000_walter-white-lg.jpg\",\"better_call_saul_appearance\":[],\"occupation\":[\"High School Chemistry Teacher\",\"Meth King Pin\"],\"appearance\":[1,2,3,4,5],\"portrayed\":\"Bryan Cranston\",\"name\":\"Walter White\",\"nickname\":\"Heisenberg\",\"char_id\":1,\"category\":\"Breaking Bad\",\"status\":\"Presumed dead\"}";
	
	@BeforeClass
	public void beforeClass() {
		characters = new GetCharactersResponse();
	}
	
	@Test
	public void agetWalterWhiteBirthday() {
		JSONObject obj = characters.getJSONObjectByKeyAndValue("name", "Walter White");
		System.out.println("PRINTING WALTER WHITE BIRTHDAY ---> " + characters.getJSONObjectValueByKey(obj, "birthday"));
		Assert.assertTrue(obj.toString().contains(walterWhite),
				"The Walter White JSON Data could not be successfully retrieved. Found: " + obj.toString() + ". Expected: " + walterWhite + ".");
	}
	
	@Test
	public void bgetAllCharactersAndPrintThem() {
		//Storing all characters into POJO List
		List<POJOCharacter> charactersList = characters.getAllCharactersInPOJOFormat();
		
		System.out.println("------- PRINTING CHARACTER NAME & PORTRAYED INFORMATION -------");
		for (int i = 0; i < charactersList.size(); i++) {
			POJOCharacter character = charactersList.get(i);
			System.out.println(character.toString());
		}
	}

}
