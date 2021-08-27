package apiObjects;

import org.json.JSONArray;
import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.BaseMethods;

public class BaseAPI {

	protected Response res = null;
	protected RequestSpecification request = RestAssured.given();
	protected JSONArray resArray = null;
	protected JSONObject resJson = null;
	protected BaseMethods base = new BaseMethods();
	
	//Sets the base URL
	public BaseAPI() {
		request.baseUri("https://breakingbadapi.com/api");
	}
	
	public String getJSONObjectValueByKey(JSONObject obj, String key) {
		return obj.getString(key);
	}
	
}
