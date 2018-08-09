package com.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.Reporter;

public class Config {

	String keyVal = "";
	InputStream inputStream;
	String propFileName = "config.properties";

	public String getPropValues(String propName) throws IOException {

		try {
			Reporter.log("Inside the getPropValues()", true);
			Properties prop = new Properties();
			Reporter.log("Created object of Properties class", true);
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			Reporter.log("Assigning the resourcestream of properties file to inputstream", true);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			keyVal = prop.getProperty(propName);

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} /*
			 * finally { inputStream.close();
			 * 
			 * }
			 */
		Reporter.log("Returning the keyvalue:  " + keyVal, true);
		return keyVal;

	}

}
