package InputBodyContent;

import Add.AddApi;

public class InputBodyContent extends AddApi {

	AddApi addApi = new AddApi();

	public String addApiInputBody() {
		return "{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -38.383499,\r\n" + "    \"lng\": 33.427365\r\n"
				+ "  },\r\n" + "  \"accuracy\": 90,\r\n" + "  \"name\": \"J Apartment\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"25, side layout, cohen 09\",\r\n" + "  \"types\": [\r\n" + "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n" + "  ],\r\n" + "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n" + "}";
	}

}
