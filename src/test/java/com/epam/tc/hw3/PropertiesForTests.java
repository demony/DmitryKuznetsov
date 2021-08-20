package com.epam.tc.hw3;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesForTests {

    private final Properties properties = new Properties();

    public PropertiesForTests() {

        String propertyFilePath = "src//test//resources//configs//wh3-configuration.properties";
        try (FileInputStream fileInputStream = new FileInputStream(propertyFilePath)) {
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
