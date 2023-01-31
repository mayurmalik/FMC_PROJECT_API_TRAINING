package tests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import utilities.AuthenticationToken;
import utilities.ConfigFileReader;
import utilities.JSONUtility;

public class FileUploadDownload extends AuthenticationToken {
	
	private static RequestSpecification requestSpec;

	@BeforeClass
	public void preRequisite() {

		requestSpec = new RequestSpecBuilder().setBaseUri(ConfigFileReader.getproperty("BaseUrl"))
				.addHeader("Authorization", "Bearer " + getAuthenticationToken())
				.build();
	}
	
	@Test
	public void e2eFileUploadDownloadFlow() {

		File test_image = new File("src/test/resources/tree_image.jpeg");
		

		String response = given().spec(requestSpec).multiPart(test_image).when().put("/file/upload").then()
				.statusCode(201).extract().response().prettyPrint();

		JsonPath extractor = new JsonPath(response);
		
		String image_key = extractor.getString("image_file_key");

		// downloding the above uploaded file
		
		response = given().spec(requestSpec).when().get("/file/"+image_key).then()
				.statusCode(200).extract().response().prettyPrint();
		
		
		
		
		


	}

}
