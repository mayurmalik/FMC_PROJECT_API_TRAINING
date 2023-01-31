package modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo_models.PojoUserSignup;
import utilities.APIConstant;
import utilities.RestAssuredEngine;

public class UserSignUpHelper {

	public static Response signUp(String methodName, RestAssuredEngine restAssuredEngine)
			throws JsonProcessingException {

		String payload = UserSignUpHelper.userSignupPayload();
		ContentType type = ContentType.JSON;
		String serviceEndpoint = "fmc/email-signup-automation";
		return restAssuredEngine.executeAPIMethods(methodName, serviceEndpoint, payload, type);
	}

	public static Response OtpVerification(String methodName,RestAssuredEngine restAssuredEngine,String otp ) throws JsonProcessingException {

		PojoUserSignup signup = new PojoUserSignup();

		signup.setEmail_id("Mayur.malik27@globant.com");
		signup.setFull_name("Mayur Malik");
		signup.setPhone_number("9599933072");
		signup.setPassword("fmcApp");
		signup.setOtp(otp);

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(signup);
		ContentType type = ContentType.JSON;
		String serviceEndpoint = "fmc/verify-otp";
		return restAssuredEngine.executeAPIMethods(methodName, serviceEndpoint, jsonPayload, type);
		
	}

	public static String userSignupPayload() throws JsonProcessingException {

		PojoUserSignup signup = new PojoUserSignup();
		signup.setEmail_id("Mayur.malik27@globant.com");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(signup);
		return json;
	}

	public static Response negativeFlowOtpVerification(String methodName,RestAssuredEngine restAssuredEngine) throws JsonProcessingException {

		PojoUserSignup signup = new PojoUserSignup();

		signup.setEmail_id("Mayur.malik27@globant.com");
		signup.setFull_name("Mayur Malik");
		signup.setPhone_number("9599933072");
		signup.setPassword("fmcApp");
		signup.setOtp("5677");

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(signup);
		ContentType type = ContentType.JSON;
		String serviceEndpoint = "fmc/verify-otp";
		return restAssuredEngine.executeAPIMethods(methodName, serviceEndpoint, jsonPayload, type);
	}

}
