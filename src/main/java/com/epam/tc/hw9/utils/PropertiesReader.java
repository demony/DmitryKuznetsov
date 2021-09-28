package com.epam.tc.hw9.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private final Properties properties = new Properties();
    private static final String PROPERTIES_FILE_PATH =
        "src//main//resources//com.epam.tc.hw9//project.properties";

    public PropertiesReader() {
        try (FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE_PATH)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTrelloBaseUri() {
        return properties.getProperty("trelloBaseUri");
    }

    public String getTrelloKey() {
        return properties.getProperty("trelloKey");
    }

    public String getTrelloToken() {
        return properties.getProperty("trelloToken");
    }
}
