package com.pers.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class PropertyFileUtil {
	Properties properties;

	public HashMap<String, String> ReadPropertyFile(String fileName) {

		String resourceName = fileName;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		properties = new Properties();
		try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
			properties.load(resourceStream);
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Exception is " + e.getMessage());
		}
		HashMap<String, String> mymap = new HashMap<String, String>();

		for (String key : properties.stringPropertyNames()) {
			String value = properties.getProperty(key);
			mymap.put(key, value);
		}

		return (mymap);
	}

}
