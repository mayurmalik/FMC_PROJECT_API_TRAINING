package tests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.simple.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import modules.AddReportHelper;
import modules.BooksHelper;
import modules.UserLoginHelper;
import modules.UserSignUpHelper;
import utilities.APIConstant;
import utilities.AuthenticationToken;
import utilities.ConfigFileReader;
import utilities.JSONPathExtractor;
import utilities.JSONUtility;
import utilities.RestAssuredEngine;

public class AddDeleteReport extends AuthenticationToken {
	public RestAssuredEngine engine;
	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void preRequisite() {

		engine = new RestAssuredEngine(getAuthenticationToken());
	}

	@Test
	public void e2eReportFlow() throws JsonProcessingException {

		// firstly we are signing up the user

		Response userSignupResponse = UserSignUpHelper.signUp(APIConstant.ApiMethods.POST,
				engine);

		softAssert.assertEquals(userSignupResponse.getStatusCode(), 201, "user signup falied");

		String message = JSONPathExtractor.extractor("message", userSignupResponse);

		softAssert.assertEquals("Email sent for OTP verification", message);

		// login with the above signup user

		Response userLoginResponse = UserLoginHelper.userLogin(APIConstant.ApiMethods.POST, engine);

		message = JSONPathExtractor.extractor("message", userLoginResponse);

		softAssert.assertEquals(userLoginResponse.getStatusCode(), 200, "user login failed");

		softAssert.assertEquals("User signed in successfully.", message);

		// creating the report with above logged in user

		Response createReportResponse = AddReportHelper.createReport(APIConstant.ApiMethods.POST, engine);

		JsonPath extractor = new JsonPath(createReportResponse.prettyPrint());

		softAssert.assertEquals(createReportResponse.getStatusCode(), 200, "Report addition failed");

		message = extractor.getString("message");

		String content = extractor.getString("content");

		assertEquals("Report created successfully", message);

		// fetching the above created report

		String userId = JSONUtility.getKeyValue("user_id", "src/test/resources/addReport.json");

		Response getReportResponse = AddReportHelper.getReport(APIConstant.ApiMethods.GET, engine, userId);

		softAssert.assertEquals(getReportResponse.getStatusCode(), 200, "Report addition failed");

		extractor = new JsonPath(getReportResponse.prettyPrint());

		message = extractor.getString("message");

		assertEquals("Success", message);

		// deleting the above fetched report

		Response deleteReportResponse = AddReportHelper.deleteReport(APIConstant.ApiMethods.DELETE, engine, userId,
				content);

		softAssert.assertEquals(deleteReportResponse.getStatusCode(), 200, "Report deletion failed");
		
		softAssert.assertAll();

	}

}
