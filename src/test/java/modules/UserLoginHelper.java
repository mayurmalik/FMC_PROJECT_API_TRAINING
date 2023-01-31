package modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo_models.PojoUserLogin;
import utilities.RestAssuredEngine;

public class UserLoginHelper {

	public static Response userLogin(String methodName, RestAssuredEngine restAssuredEngine)
			throws JsonProcessingException {

		String payload = UserLoginHelper.userLoginPositiveFlow();
		ContentType type = ContentType.JSON;
		String serviceEndpoint = "fmc/login";
		return restAssuredEngine.executeAPIMethods(methodName, serviceEndpoint, payload, type);

	}

	public static String userLoginPositiveFlow() throws JsonProcessingException {

		PojoUserLogin login = new PojoUserLogin();
		login.setEmail_id("Mayur.malik27@globant.com");
		login.setPassword("fmcApp");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(login);
		return json;
	}

	public static Response userLoginNegativeFlow(String methodName, RestAssuredEngine restAssuredEngine)
			throws JsonProcessingException {

		PojoUserLogin login = new PojoUserLogin();
		login.setEmail_id("Mayur.malik845@globant.com");
		login.setPassword("Apache2154##");
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(login);
		ContentType type = ContentType.JSON;
		String serviceEndpoint = "fmc/login";
		return restAssuredEngine.executeAPIMethods(methodName, serviceEndpoint, jsonPayload, type);

	}

}
