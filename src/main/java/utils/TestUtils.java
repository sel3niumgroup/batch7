package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TestUtils {

	/**
	 * This method parses the JSON file and returns value of the Key
	 * <br><b>Ex: jsonHandler("data.json","loginDetails",0,"url");
	 * @param fileName
	 * @param arrayName
	 * @param index
	 * @param key
	 * @return
	 */
	public static String jsonHandler(String fileName,String arrayName, int index,String key) {
		try {
			JSONParser jsonParser = new JSONParser();		
			Object obj = jsonParser.parse(new FileReader("./testData/"+fileName));
			JSONObject jsonObj = (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObj.get(arrayName);
			JSONObject Obj = (JSONObject) jsonArray.get(index);
			String str = (String) Obj.get(key);
			return str;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
