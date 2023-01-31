package utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONUtility {

	public static String getKeyValue(String key, String filepath) {
		String value = "";
		// Creating a JSONParser object
		JSONParser jsonParser = new JSONParser();
		try {
			// Parsing the contents of the JSON file
			JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(filepath));
			JSONObject mapdata = (JSONObject) jsonObject.get("reporter_details");
			value=(String) mapdata.get(key);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return value;

	}

	public static JSONObject setKeyValue(String key, String value, String filepath) {
		JSONObject jsonObject = null;
		// Creating a JSONParser object
		JSONParser jsonParser = new JSONParser();
		try {
			// Parsing the contents of the JSON file
			 jsonObject = (JSONObject) jsonParser.parse(new FileReader(filepath));
			JSONObject fetchedData=(JSONObject) jsonObject.get("reporter_details");
			fetchedData.put(key, value);
			jsonObject.put("reporter_details", fetchedData);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return jsonObject;
	}
}
