package com.epam.tc.hw6;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesForTests {

    private final Properties properties = new Properties();
    private static final String PROPERTIES_FILE_PATH = "src//test//resources//configs//wh3-configuration.properties";

    public PropertiesForTests() {
        try (FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE_PATH)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUrlMainPage() {
        return properties.getProperty("urlMainPage");
    }

    public String getUrlDifferentElementPage() {
        return properties.getProperty("urlDifferentElementPage");
    }

    public String getUserLogin() {
        return properties.getProperty("userLogin");
    }

    public String getUserPassword() {
        return properties.getProperty("userPassword");
    }

    public String getUserName() {
        return properties.getProperty("userName");
    }

    public Integer getImplicitlyWait() {
        return Integer.parseInt(properties.getProperty("implicitlyWait"));
    }
}
