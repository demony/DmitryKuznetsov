package com.epam.tc.hw5.cucumber.context;

import java.util.HashMap;
import java.util.Map;

public final class TestContext {

    private static TestContext instance;
    private Map<String, Object> context = new HashMap<>();

    private TestContext() {
    }

    public void addTestObject(String key, Object value) {
        context.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T getTestObject(String key) {
        return (T) context.get(key);
    }

    public void clean() {
        context.clear();
        instance = null;
    }

    public static TestContext getInstance() {
        if (instance == null) {
            instance = new TestContext();
        }
        return instance;
    }
}
