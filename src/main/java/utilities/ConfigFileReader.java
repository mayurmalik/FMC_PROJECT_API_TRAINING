package utilities;
import java.io.FileInputStream;
import java.util.Properties;
public class ConfigFileReader {

	public static String getproperty(String key) {

		Properties file = new Properties();

		try {

			FileInputStream fs = new FileInputStream(
					"C:\\Users\\MMalik3\\Downloads\\API_Project_FMC\\APIDemo_FMC\\src\\test\\resources\\config.properties");
			file.load(fs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file.getProperty(key);
	}

}
