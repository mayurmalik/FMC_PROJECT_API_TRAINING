package utilities;

import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class AuthenticationToken {

	public String getAuthenticationToken() {
//		File file = new File("./src/test/resources/auth.json");
//		RequestSpecBuilder builder = new RequestSpecBuilder(); 
//		String validRequest = "{\n" +
//	            "  \"userName\": \"smita.shewale\",\n" +
//	            "  \"password\": \"Smita@123\" \n}";
////		Map<String, String> payload = new HashMap<String, String>();
////		payload.put("username", "mahesh");
////		payload.put("password", "mahesh");
//		//builder.setBody(validRequest);
//		
//		//builder.setBody(file);
//		RequestSpecification requestSpecification = builder.build();
//        RequestSpecification request = RestAssured.given();
//        request.contentType(ContentType.JSON);
//        request.spec(requestSpecification);
//        request.body(file);
//        String token = request.post("http://fmc-env.eba-5akrwvvr.us-east-1.elasticbeanstalk.com/fmc/token").getBody().asString();
//        System.out.println("token"+token);
//        return token;
        
		RestAssured.baseURI = "http://fmc-env.eba-5akrwvvr.us-east-1.elasticbeanstalk.com/";

		String response = given().when().log().all().get("fmc/token").then().extract().response().prettyPrint();

		JsonPath extractor = new JsonPath(response);

		// here we are getting the token from response

		String token = extractor.getString("accessToken");
		return token;
	}
}
