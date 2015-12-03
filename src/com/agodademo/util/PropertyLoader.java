package com.agodademo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
	public static Properties getProperty() {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			String filename = "config.properties";
			input = PropertyLoader.class.getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Unable to find the db path mentioned in config.properties  " + filename);
				System.exit(1);
			}

			// load a properties file from class path, inside static method
			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
}
