package Get;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class DemoGet {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		given().log().all().queryParam("key", "qaclick123").queryParam("place_id", "288af933b43f79b004145ac27a8d8136")
				.when().log().all().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200);
	}

}
