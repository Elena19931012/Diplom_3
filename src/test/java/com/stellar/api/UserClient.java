package com.stellar.api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class UserClient {
    
    private RequestSpecification getBaseSpec() {
        return RestAssured.given()
                .baseUri(Endpoints.BASE_URL)
                .header("Content-Type", "application/json");
    }
    
    @Step("Register user via API with email: {email}")
    public Response registerUser(String email, String password, String name) {
        Map<String, String> user = new HashMap<>();
        user.put("email", email);
        user.put("password", password);
        user.put("name", name);
        
        return getBaseSpec()
                .body(user)
                .when()
                .post(Endpoints.REGISTER);
    }
    
    @Step("Login user via API with email: {email}")
    public Response loginUser(String email, String password) {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);
        
        return getBaseSpec()
                .body(credentials)
                .when()
                .post(Endpoints.LOGIN);
    }
    
    @Step("Delete user via API")
    public Response deleteUser(String accessToken) {
        return getBaseSpec()
                .auth().oauth2(accessToken.replace("Bearer ", ""))
                .when()
                .delete(Endpoints.USER);
    }
}
