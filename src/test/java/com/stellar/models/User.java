package com.stellar.models;

import java.util.UUID;

public class User {
    private String email;
    private String password;
    private String name;
    private String accessToken;
    
    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
    
    public static User random() {
        String uuid = UUID.randomUUID().toString();
        return new User(
            "test-user-" + uuid + "@example.com",
            "password123",
            "Test User " + uuid
        );
    }
    
    public static User randomWithShortPassword() {
        String uuid = UUID.randomUUID().toString();
        return new User(
            "test-user-" + uuid + "@example.com",
            "12345", // Short password
            "Test User " + uuid
        );
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getName() {
        return name;
    }
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public String getAccessToken() {
        return accessToken;
    }
}
