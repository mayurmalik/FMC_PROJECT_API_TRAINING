package modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo_models.PojoPasswordReset;
import utilities.RestAssuredEngine;

public class PasswordHelper {

	public static Response passwordReset(String methodName, RestAssuredEngine restAssuredEngine)
			throws JsonProcessingException {

		String payload = UserLoginHelper.userLoginPositiveFlow();
		ContentType type = ContentType.JSON;
		String serviceEndpoint = "fmc/reset-password";

		return restAssuredEngine.executeAPIMethods(methodName, serviceEndpoint, payload, type);
	}

	public static String passwordResetValidData() throws JsonProcessingException {

		PojoPasswordReset pwdReset = new PojoPasswordReset();

		pwdReset.setEmail_id("Mayur.malik27@globant.com");
		pwdReset.setPassword("Dzire34667$$");

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pwdReset);
		return json;
	}

	public static Response passwordResetInvalidData(String methodName, RestAssuredEngine restAssuredEngine)
			throws JsonProcessingException {

		PojoPasswordReset pwdReset = new PojoPasswordReset();

		pwdReset.setEmail_id("rahul.malikk@globant.com");
		pwdReset.setPassword("Dzire34667$$");

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pwdReset);
		ContentType type = ContentType.JSON;
		String serviceEndpoint = "fmc/reset-password";
		return restAssuredEngine.executeAPIMethods(methodName, serviceEndpoint, jsonPayload, type);
	}

}
