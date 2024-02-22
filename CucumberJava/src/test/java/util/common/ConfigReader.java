package util.common;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	private static final String PROPERTY_FILE ="config.properties";
	private static final Properties properties = new Properties();
	
	static {
		try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(PROPERTY_FILE)){
			properties.load(input);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getBasePath() {
		return properties.getProperty("base.path");
	}
	
	public static String getDriverPath() {
		return properties.getProperty("driver.path");
	}
}
