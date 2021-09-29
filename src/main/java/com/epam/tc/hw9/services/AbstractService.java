package com.epam.tc.hw9.services;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import org.apache.http.HttpStatus;

public abstract class AbstractService {

    protected RequestSpecification requestSpecification;

    public AbstractService(String baseUri, String basePath, String key, String token) {
        String authorization =
            "OAuth oauth_consumer_key=\"" + key + "\", oauth_token=\"" + token + "\"";
        requestSpecification =
            new RequestSpecBuilder()
                .addHeader("Authorization", authorization)
                .setBaseUri(baseUri)
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .build();
    }

    protected Response makeRequest(Method method, String uri) {
        Response response = given(requestSpecification).request(method, uri);
        return response;
    }

    protected Response makeRequest(Method method, String uri, Map<String, Object> queryParams) {
        RequestSpecification specification = given(requestSpecification);
        addParameters(specification, queryParams);
        Response response = specification.request(method, uri);
        return response;
    }

    protected Response getRequest(String uri) {
        return makeRequest(Method.GET, uri);
    }

    protected Response getRequest(String uri, Map<String, Object> queryParams) {
        return makeRequest(Method.GET, uri, queryParams);
    }

    protected Response putRequest(String uri) {
        return makeRequest(Method.PUT, uri);
    }

    protected Response putRequest(String uri, Map<String, Object> queryParams) {
        return makeRequest(Method.PUT, uri, queryParams);
    }

    protected Response postRequest(String uri) {
        return makeRequest(Method.POST, uri);
    }

    protected Response postRequest(String uri, Map<String, Object> queryParams) {
        return makeRequest(Method.POST, uri, queryParams);
    }

    protected Response deleteRequest(String uri) {
        return makeRequest(Method.DELETE, uri);
    }

    protected void addParameters(RequestSpecification requestSpecification, Map<String, Object> queryParams) {
        for (Map.Entry<String, Object> param : queryParams.entrySet()) {
            requestSpecification.queryParam(param.getKey(), param.getValue());
        }
    }

    public <T> T prepareResult(Response response, Class<T> expectedClass) {
        if (response.getStatusCode() == HttpStatus.SC_OK) {
            return response.as(expectedClass);
        } else if (response.getStatusCode() == HttpStatus.SC_NOT_FOUND) {
            return null;
        } else {
            response.then().log().ifError();
            return null;
        }
    }
}
