package utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JSONPathExtractor {

	public static String extractor(String key, Response response) {

		JsonPath extractor = new JsonPath(response.prettyPrint());

		String value = extractor.getString(key);

		return value;

	}

}
