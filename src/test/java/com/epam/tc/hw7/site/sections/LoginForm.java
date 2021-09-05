package com.epam.tc.hw7.site.sections;

import com.epam.jdi.light.elements.composite.Form;
import com.epam.tc.hw7.entities.User;
import org.openqa.selenium.WebElement;

public class LoginForm extends Form<User> {
    public WebElement name;
    public WebElement password;
    public WebElement loginButton;
}
