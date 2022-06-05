package com.appium2.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFactory {

	private static ConfigFactory configFactory;

	// Create Singleton class
	private ConfigFactory() {

	}

	public static ConfigFactory getInstance() {
		if(configFactory == null) {
			configFactory = new ConfigFactory();
			return configFactory;
		} else {
			return configFactory;
		}
	}

	public Properties loadPropertyFile(File file) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(file));
		} catch (IOException e) {
			System.err.println("Unable to load config file : " + e.getMessage());
		}
		return properties;
	}

}
