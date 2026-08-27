
package com.pers.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class ConfigReader {
	public static HashMap<String, String> configMap = new HashMap<String, String>();

	public static HashMap<String, String> readConfigFile(String filepath) {
		Properties pro = new Properties();
		InputStream resourceStream;

		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			resourceStream = loader.getResourceAsStream(filepath);

			// Using FileInputStream to read the file
//			resourceStream = new FileInputStream(filepath);
			pro.load(resourceStream);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception is " + e.getMessage());
		}

		for (String key : pro.stringPropertyNames()) {
			String value = pro.getProperty(key);
			configMap.put(key, value);
		}

		for (Object key : configMap.keySet()) {
			System.out.println("Key : " + key.toString() + " Value : " + configMap.get(key));
		}

		return configMap;
	}

}
