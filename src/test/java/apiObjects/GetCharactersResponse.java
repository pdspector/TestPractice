package apiObjects;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import resources.POJOCharacter;

public class GetCharactersResponse extends BaseAPI {

	public GetCharactersResponse() {
		super();
		//Sets the endpoint URL for this Class
		request.basePath("/characters");
 		//try {
			res = request.given().when().get();
			resArray = new JSONArray(res.asString());
		/*} catch (Exception e) {
			throw new RuntimeException("Something went wrong with the EndPointCall \n" + e.toString());
		}*/
	}
	
	public JSONObject getJSONObjectByKeyAndValue(String key, String value) {
		JSONObject obj = null;
		for (int i = 0; i < resArray.length(); i++) {
			JSONObject obj2 = null;
			try {
				obj2 = resArray.getJSONObject(i);
			} catch (Exception e) {
				
			}
			if (obj2 != null) {
				if (obj2.getString(key).equalsIgnoreCase(value)) {
					obj = obj2;
				}
			}
		}
		return obj;
	}
	
	public List<POJOCharacter> getAllCharactersInPOJOFormat() {
		List<POJOCharacter> characters = new ArrayList<POJOCharacter>();
		for (int i = 0; i < resArray.length(); i++) {
			JSONObject obj2 = null;
				obj2 = resArray.getJSONObject(i);
				characters.add(base.createPOJO(obj2));

		}
		return characters;
	}
	
}
