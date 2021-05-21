package com.example.actvn.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

	public static Properties getProperties(String propertyFileName) throws Exception {
		final String configPath = System.getProperty("configPath");
		final String filePath = (configPath == null ? "" : configPath + "/") + propertyFileName;
		return getPropertiesFromFile(filePath);
	}

	public static Properties getProperties(String absoluteFilePath, String fileName) throws Exception {
		final String filePath = absoluteFilePath + File.separatorChar + fileName;
		return getPropertiesFromFile(filePath);
	}

	public static Properties getPropertiesFromFile(String filePath) throws Exception {
		InputStream is = null;
		try {
			final File file = new File(filePath);
			is = new FileInputStream(file);

			// load properties
			Properties props = new Properties();
			props.load(is);
			return props;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}
	}

	public static String getFullPath(String fileName) throws Exception {
		if(fileName == null) return null;
		String configPath = System.getProperty("configPath");
		if (configPath == null) {
			configPath = "";
		}
		return configPath + File.separatorChar + fileName;
	}
}
