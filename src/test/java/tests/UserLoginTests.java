package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.restassured.response.Response;
import modules.UserLoginHelper;
import modules.UserSignUpHelper;
import utilities.APIConstant;
import utilities.AuthenticationToken;
import utilities.JSONPathExtractor;
import utilities.RestAssuredEngine;

public class UserLoginTests extends AuthenticationToken {
	public RestAssuredEngine engine;
	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void preRequisite() {

		engine = new RestAssuredEngine(getAuthenticationToken());

	}

	@Test
	public void userLogin() throws JsonProcessingException {

		Response userLoginResponse = UserLoginHelper.userLogin(APIConstant.ApiMethods.POST, engine);

		// validating the success message after login

		String message = JSONPathExtractor.extractor("message", userLoginResponse);

		softAssert.assertEquals(userLoginResponse.getStatusCode(), 200, "user login failed");

		softAssert.assertEquals("User signed in successfully.", message);

	}

	// negative flow trying to login with incorrect user
	@Test
	public void userLoginNegativeFlow() throws JsonProcessingException {

		Response userLoginResponse = UserLoginHelper.userLoginNegativeFlow(APIConstant.ApiMethods.POST, engine);

		// validating the success message after login

		String message = JSONPathExtractor.extractor("message", userLoginResponse);

		softAssert.assertEquals(userLoginResponse.getStatusCode(), 401, "user login with invalid creds");

		softAssert.assertEquals("Authentication Failure", message);

	}

}
