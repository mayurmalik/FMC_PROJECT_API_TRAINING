package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import modules.PasswordHelper;
import utilities.APIConstant;
import utilities.AuthenticationToken;
import utilities.JSONPathExtractor;
import utilities.RestAssuredEngine;

public class PasswordReset extends AuthenticationToken {
	public RestAssuredEngine engine;
	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void preRequisite() {

		engine = new RestAssuredEngine(getAuthenticationToken());
	}

	@Test
	public void passwordReset() throws JsonProcessingException {

		Response passwordResetResponse = PasswordHelper.passwordReset(APIConstant.ApiMethods.PUT, engine);

		softAssert.assertEquals(passwordResetResponse.getStatusCode(), 200, "password reset operation failed");

		String message = JSONPathExtractor.extractor("message", passwordResetResponse);

		softAssert.assertEquals("Password reset successfully.", message);

	}

	// here we are trying to reset the password with incorrect email id we should
	// get 401 as status code and email not
	// registered as response message
	@Test
	public void passwordResetNegativeFlow() throws JsonProcessingException {

		Response passwordResetResponse = PasswordHelper.passwordResetInvalidData(APIConstant.ApiMethods.PUT, engine);

		softAssert.assertEquals(passwordResetResponse.getStatusCode(), 401,
				"user should not be able to reset the password with incorrect email");

		String message = JSONPathExtractor.extractor("message", passwordResetResponse);

		softAssert.assertEquals("Email ID not registered", message);

	}

}
