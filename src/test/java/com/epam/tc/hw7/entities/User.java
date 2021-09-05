package com.epam.tc.hw7.entities;

import com.epam.jdi.tools.DataClass;

public class User extends DataClass<User> {

    public static final User USER_ROMAN = new User(
        "Roman",
        "Jdi1234",
        "Roman Iovlev");

    public String name;
    public String password;
    public String fullName;

    public User(String login, String password, String visibleName) {
        this.name = login;
        this.password = password;
        this.fullName = visibleName;
    }
}
