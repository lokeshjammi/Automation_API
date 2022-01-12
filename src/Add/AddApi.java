package Add;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import InputBodyContent.InputBodyContent;
import RawToJson.RawToJson;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AddApi {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		InputBodyContent inputBodyContent = new InputBodyContent();
		
		/**
		 * Add Place API
		 */
		String responses = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(inputBodyContent.addApiInputBody()).when().post("/maps/api/place/add/json").then().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)").extract()
				.response().asString();
		System.out.println(responses);
		JsonPath jsp = new JsonPath(responses);
		String place_id = jsp.getString("place_id");
		System.out.println(place_id);

		/**
		 * Update Place API
		 */

		String update_response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + place_id + "\",\r\n" + "\"address\":\"701 Summer walk, USA\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated")).extract().response().asString();
		System.out.println(update_response);
		JsonPath updateJsonPath = new JsonPath(update_response);
		String updatedMessage = updateJsonPath.getString("msg");
		System.out.println(updatedMessage);
		String expectedMessage = "701 Summer walk, USA";
		System.out.println(expectedMessage);

		/**
		 * Get Place API
		 */

		String get_response = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id)
				.when().get("maps/api/place/get/json").then().assertThat().statusCode(200).extract().response()
				.asString();
		JsonPath getJsonResponse = RawToJson.rawToJson(get_response);
		String getResponse = getJsonResponse.getString("address");
		System.out.println(getResponse);
		Assert.assertEquals(expectedMessage, getResponse);
	}
}
