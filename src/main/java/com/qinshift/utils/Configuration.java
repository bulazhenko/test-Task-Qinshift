package com.qinshift.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Configuration {

    private static final Logger logger = LoggerFactory.getLogger(Configuration.class);
    private static final String PROPERTIES_FILE_PATH = "src/test/resources/";

    /**
     * Loads properties from the specified file.
     * @param filename The name of the properties file (without the .properties extension).
     * @return A Properties object containing the loaded properties, or an empty Properties object if the file is not found or an error occurs.
     */
    public static Properties getProperties(String filename) {
        Properties properties = new Properties();
        File propertiesFile = new File(PROPERTIES_FILE_PATH + filename + ".properties");

        if (!propertiesFile.exists()) {
            logger.error("File Not Found: {}", filename);
            return properties;
        }

        try (FileInputStream fileInputStream = new FileInputStream(propertiesFile)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            logger.error("Error loading properties file: {}", filename, e);
        }
        return properties;
    }
}
