package TestNG_Script;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import InputBodyContent.InputBodyContent;
import io.restassured.path.json.JsonPath;

public class ApiAutomation {

	@Test
	public void runScript() {
		/**
		 * Courses Count
		 */
		String jsonResponse = InputBodyContent.CourseAPI();
		System.out.println(jsonResponse);
		JsonPath jsonParse = new JsonPath(jsonResponse);
		System.out.println(jsonParse);
		//		int courseCount = jsonParse.getInt("courses.size()");
		//		System.out.println(courseCount);
		List<String> courses = new ArrayList<String>();
		courses = jsonParse.getList("courses");
		System.out.println(courses);
		int coursesCount = courses.size();
		System.out.println(coursesCount);

		/**
		 * Print Purchase amount
		 */

		int purchaseAmount = jsonParse.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);

		/**
		 * Get title of first course
		 */
		String firstCourseTitle = jsonParse.getString("courses[0].title");
		System.out.println(firstCourseTitle);

		/**
		 * Print all courses and respective prices
		 */
		for(int i = 0; i < coursesCount; i++) {
			String coursesTitle = jsonParse.getString("courses["+i+"].title");
			int coursePrice = jsonParse.getInt("courses["+i+"].price");
			System.out.println("Title of course is: "+coursesTitle+" and price is: "+coursePrice);
		}

		/**
		 * Validate a title and print copy sold
		 */
		for(int i = 0; i < coursesCount; i++) {
			String coursesTitle = jsonParse.getString("courses["+i+"].title");
			if(coursesTitle.equalsIgnoreCase("RPA")) {
				int copiesSold = jsonParse.getInt("courses["+i+"].copies");
				System.out.println("Course Name is: "+coursesTitle+" And copies sold is: "+copiesSold);
				break;
			}
		}

		/**
		 * Validate sum of all copies is equal to purchaseAmount
		 */
		purchaseAmount = jsonParse.getInt("dashboard.purchaseAmount");
		int finalPrice = 0;
		for(int i = 0; i < coursesCount; i++) {
			int coursePrice = jsonParse.getInt("courses["+i+"].price");
			int copiesSold = jsonParse.getInt("courses["+i+"].copies");
			finalPrice = finalPrice + (coursePrice * copiesSold);
		}
		System.out.println("All courses final price is: "+finalPrice);
		try {
			Assert.assertEquals(purchaseAmount, finalPrice);
			System.out.println("Purchase amount and final amount is equal");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

