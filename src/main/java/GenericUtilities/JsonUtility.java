package GenericUtilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author B.Nandini This class is used to fetch data from json file
 */
public class JsonUtility {

	/**
	 * This method is used to fetch data from json file using key
	 * 
	 * @param key
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public String fetchDataFromJsonFile(String key) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("./src/test/resources/VtigerCD.json"));
		JSONObject js = (JSONObject) obj;
		String data = js.get(key).toString();
		return data;
	}

}
