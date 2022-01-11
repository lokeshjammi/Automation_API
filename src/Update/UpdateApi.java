package Update;

import static io.restassured.RestAssured.given;

import InputBodyContent.InputBodyContent;
import io.restassured.RestAssured;

public class UpdateApi {

	public static void main(String[] args) {
		InputBodyContent inputBodyContent = new InputBodyContent();
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json").body("")
				.when().put("maps/api/place/update/json").then().assertThat().statusCode(200).extract().response()
				.asString();

		System.out.println(response);
	}

}
