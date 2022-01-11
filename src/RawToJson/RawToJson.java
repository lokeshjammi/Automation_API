package RawToJson;

import io.restassured.path.json.JsonPath;

public class RawToJson {

	public static JsonPath rawToJson(String extractedResponse) {
		JsonPath getJsonPath = new JsonPath(extractedResponse);
		return getJsonPath;
	}

}
