package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import modules.UserSignUpHelper;
import utilities.APIConstant;
import utilities.AuthenticationToken;
import utilities.JSONPathExtractor;
import utilities.RestAssuredEngine;

public class UserSignUpTests extends AuthenticationToken {
	public RestAssuredEngine engine;
	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void preRequisite() {

		engine = new RestAssuredEngine(getAuthenticationToken());

	}

	@Test
	public void emailSignup() throws JsonProcessingException {

		Response userSignupResponse = UserSignUpHelper.signUp(APIConstant.ApiMethods.POST, engine);

		softAssert.assertEquals(userSignupResponse.getStatusCode(), 201, "user signup falied");

		String message = JSONPathExtractor.extractor("message", userSignupResponse);

		softAssert.assertEquals("Email sent for OTP verification", message);

	}

	@Test
	public void verifyOtpPositiveSceanrio() throws JsonProcessingException {

		Response userSignupResponse = UserSignUpHelper.signUp(APIConstant.ApiMethods.POST, engine);

		// fetching the otp from user signup response

		String otp = JSONPathExtractor.extractor("otp", userSignupResponse);

		Response verifyOtpResponse = UserSignUpHelper.OtpVerification(APIConstant.ApiMethods.PUT, engine, otp);

		softAssert.assertEquals(verifyOtpResponse.getStatusCode(), 200, "otp verification failed");

		String message = JSONPathExtractor.extractor("message", verifyOtpResponse);

		softAssert.assertEquals("Signed up successfully", message);

	}

	@Test
	public void verifyOtpNegativeSceanrio() throws JsonProcessingException {

		Response verifyNegativeOtpFlowResponse = UserSignUpHelper
				.negativeFlowOtpVerification(APIConstant.ApiMethods.PUT, engine);

		softAssert.assertEquals(verifyNegativeOtpFlowResponse.getStatusCode(), 400, "bad request sent");

		String message = JSONPathExtractor.extractor("message", verifyNegativeOtpFlowResponse);

		softAssert.assertEquals("OTP invalid", message);

	}

}
