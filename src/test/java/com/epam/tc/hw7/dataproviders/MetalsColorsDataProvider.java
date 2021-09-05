package com.epam.tc.hw7.dataproviders;

import com.epam.tc.hw7.entities.MetalsColorsDataSet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import org.testng.annotations.DataProvider;


public class MetalsColorsDataProvider {

    private static final String DATA_JSON_FILE =
        "src//test//resources//com.epam.tc.hw7//JDI_ex8_metalsColorsDataSet.json";

    public static Map<String, MetalsColorsDataSet> readDataSetJson() {
        try (FileInputStream inputStream = new FileInputStream(DATA_JSON_FILE)) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(inputStream, new TypeReference<Map<String, MetalsColorsDataSet>>(){});
        } catch (IOException e) {
            throw new RuntimeException("Can't read file with test data: " + DATA_JSON_FILE);
        }
    }

    @DataProvider(name = "metalsColorsDataSet")
    public static Object[][] getMetalsColorsDataSet() {
        return readDataSetJson()
            .entrySet()
            .stream()
            .map(e -> new Object[]{e.getKey(), e.getValue()}).toArray(Object[][]::new);
    }
}
