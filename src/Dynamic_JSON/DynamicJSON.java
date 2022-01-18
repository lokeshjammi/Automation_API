package Dynamic_JSON;

import org.testng.Assert;
import org.testng.annotations.Test;

import RawToJson.RawToJson;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJSON {

	@Test
	public void addBook() {
		RestAssured.baseURI = "http://216.10.245.166/";

		String addResponse = given().header("Content-Type", "application/json").body(DynamicJSONInputBody.addBookApi())
				.when().post("Library/Addbook.php").then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(addResponse);
		JsonPath addJsonResponse = RawToJson.rawToJson(addResponse);
		System.out.println(addJsonResponse);
		String expectedMessage = "successfully added";
		String actualMessage = addJsonResponse.getString("Msg");
		String actualID = addJsonResponse.getString("ID");
		try {
			Assert.assertEquals(expectedMessage, actualMessage);
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println(actualID);
	}
}
