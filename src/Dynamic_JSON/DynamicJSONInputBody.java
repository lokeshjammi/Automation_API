package Dynamic_JSON;

public class DynamicJSONInputBody {

	public static String addBookApi(String bookName, String isbn, String aisle) {
		String addBookBody = "{\r\n" + "\"name\":\"" + bookName + "\",\r\n" + "\"isbn\":\"" + isbn + "\",\r\n"
				+ "\"aisle\":\"" + aisle + "\",\r\n" + "\"author\":\"Lokesh\"\r\n" + "}";
		return addBookBody;
	}

	public static String deleteBookApi(String actualID) {
		String deleteBookBody = "{\r\n" + "\"ID\" : \"" + actualID + "\" \r\n" + "} \r\n" + "";
		return deleteBookBody;
	}
}
