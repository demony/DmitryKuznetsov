package com.epam.tc.hw7.entities;

public enum LeftMenuData {
    Home("Home"),
    ContactForm("Contact form"),
    Support("Support"),
    Dates("Dates"),
    Service("Service"),
    ComplexTable("Complex Table"),
    SimpleTable("Simple Table"),
    UserTable("User Table"),
    TableWithPages("Table with pages"),
    DifferentElements("Different elements"),
    MetalsColors("Metals & Colors"),
    Performance("Performance"),
    ElementsPacks("Elements packs"),
    HTML5("HTML 5"),
    Bootstrap("Bootstrap");

    public String value;
    LeftMenuData(String value) {
        this.value = value;
    }
}
