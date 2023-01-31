package modules;

import org.json.simple.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.JSONUtility;
import utilities.RestAssuredEngine;

public class AddReportHelper {

	public static Response createReport(String methodName, RestAssuredEngine restAssuredEngine)
			throws JsonProcessingException {

		String serviceEndpoint = "fmc/reports";
		ContentType type = ContentType.JSON;

		JSONObject payLoad = JSONUtility.setKeyValue("request_id", Double.toString(Math.random() * 9999),
				"src/test/resources/addReport.json");

		return restAssuredEngine.executeAPIMethods(methodName, serviceEndpoint, payLoad.toString(), type);
	}

	public static Response getReport(String methodName, RestAssuredEngine restAssuredEngine, String userId)
			throws JsonProcessingException {

		String serviceEndpoint = "fmc/reports/" + userId;
		ContentType type = ContentType.JSON;
		return restAssuredEngine.executeAPIMethods(methodName, serviceEndpoint, "", type);
	}

	public static Response deleteReport(String methodName, RestAssuredEngine restAssuredEngine, String userId,
			String content) throws JsonProcessingException {

		String serviceEndpoint = "fmc/reports/" + content + "/users/" + userId;
		ContentType type = ContentType.JSON;
		return restAssuredEngine.executeAPIMethods(methodName, serviceEndpoint, "", type);
	}

}
