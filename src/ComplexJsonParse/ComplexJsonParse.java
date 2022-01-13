package ComplexJsonParse;

import java.util.ArrayList;
import java.util.List;

import InputBodyContent.InputBodyContent;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		
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
	}

}
