package com.epam.tc.hw7.entities;

import java.util.List;

public class MetalsColorsDataSet {
    public Integer[] summary;
    public List<String> elements;
    public String color;
    public String metals;
    public List<String> vegetables;

    public String getSummaryAsLog() {
        return "Summary: " + (summary[0] + summary[1]);
    }

    public String getElementsAsLog() {
        return "Elements: " + String.join(", ", elements);
    }

    public String getColorAsLog() {
        return "Color: " + color;
    }

    public String getMetalsAsLog() {
        return "Metal: " + metals;
    }

    public String getVegetablesAsLog() {
        return "Vegetables: " + String.join(", ", vegetables);
    }

}
