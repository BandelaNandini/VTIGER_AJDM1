package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author B.Nandini This class is declare to fetch data and to write back data
 *         from property file
 */
public class PropertyFileUtility {

	/**
	 * This method is declared to fetch data from property file
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String fetchDataFromPropFile(String key) throws IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/commondata.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String value = prop.getProperty(key);
		return value;
	}

	/**
	 * This method is declared to write back data to property file
	 * 
	 * @param key
	 * @param value
	 * @throws IOException
	 */
	public void writeBackDataToPropFile(String key, String value) throws IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/commondata.properties");
		Properties prop = new Properties();
		prop.load(fis);
		prop.put(key, value);
		FileOutputStream fos = new FileOutputStream("./src/test/resources/commondata.properties");
		prop.store(fos, "Updated");
	}

}
