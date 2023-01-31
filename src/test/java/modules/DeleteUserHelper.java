package modules;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo_models.PojoPasswordReset;
import utilities.RestAssuredEngine;

public class DeleteUserHelper {
	
	public static Response deleteuser(String methodName, RestAssuredEngine restAssuredEngine) throws JsonProcessingException {

		JSONObject deleteUserBody = new JSONObject();
		deleteUserBody.put("email_id", "Mayur.malik27@globant.com");
		ContentType type = ContentType.JSON;
		String serviceEndpoint = "fmc/delete-user";
		return restAssuredEngine.executeAPIMethods(methodName, serviceEndpoint, deleteUserBody.toString(), type);
	}

	public static Response deleteUserNegativeFlow(String methodName, RestAssuredEngine restAssuredEngine) throws JsonProcessingException {

		JSONObject deleteUserBody = new JSONObject();
		deleteUserBody.put("email_id", "Rahul.malik@globant.com");
		ContentType type = ContentType.JSON;
		String serviceEndpoint = "fmc/delete-user";
		return restAssuredEngine.executeAPIMethods(methodName, serviceEndpoint, deleteUserBody.toString(), type);
	}
	
	public static Response userLoginValidationAfterDelete(String methodName, RestAssuredEngine restAssuredEngine) throws JsonProcessingException {
		JSONObject loginBody = new JSONObject();
		loginBody.put("email_id", "Mayur.malik27@globant.com");
		loginBody.put("password", "fmcApp");
		ContentType type = ContentType.JSON;
		String serviceEndpoint = "fmc/login";
		return restAssuredEngine.executeAPIMethods(methodName, serviceEndpoint, loginBody.toString(), type);
	}

}
