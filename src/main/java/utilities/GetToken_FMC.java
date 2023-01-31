package utilities;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class GetToken_FMC {

	public static String generateToken() {

		RestAssured.baseURI = "http://fmc-env.eba-5akrwvvr.us-east-1.elasticbeanstalk.com/";
		
		String response = given().when().log().all().get("fmc/token").then().extract().response().prettyPrint();
		
        JsonPath extractor = new JsonPath(response);
		
		// here we are getting the token from response
		
		String token = extractor.getString("accessToken");
		return token;

	}
}
