package Dynamic_JSON;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RawToJson.RawToJson;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJSON {

	@Test(priority = 1, dataProvider = "BooksData")
	public void addBook(String bookName, String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String addResponse = given().header("Content-Type", "application/json")
				.body(DynamicJSONInputBody.addBookApi(bookName, isbn, aisle)).when().post("/Library/Addbook.php").then()
				.assertThat().statusCode(200).extract().response().asString();
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
		String deleteBody = DynamicJSONInputBody.deleteBookApi(actualID);
		deleteBook(deleteBody);
	}

	public void deleteBook(String deleteBody) {
		String deleteResponse = given().log().all().body(deleteBody).when().post("/Library/DeleteBook.php").then().log()
				.all().assertThat().statusCode(200).extract().response().asString();
		System.out.println(deleteResponse);
		JsonPath deleteJson = RawToJson.rawToJson(deleteResponse);
		System.out.println(deleteJson);
		String expectedMessage = "book is successfully deleted";
		String actualMessage = deleteJson.getString("msg");
		try {
			Assert.assertEquals(actualMessage, expectedMessage);
			System.out.println("Book deleted successfully");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	@DataProvider(name = "BooksData")
	public Object[][] getData() {
		return new Object[][] { { "Lokesh", "Lok", "789" }, { "Jammi", "Jam", "360" } };
	}
}