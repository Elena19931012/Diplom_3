package com.stellar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.datafaker.Faker;

@Data
@AllArgsConstructor
public class User {
    private String email;
    private String password;
    private String name;
    private String accessToken;
    
    private static final Faker faker = new Faker();
    
    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.accessToken = null;
    }
    
    public static User random() {
        return new User(
            faker.internet().emailAddress(),
            "password" + faker.number().digits(3),
            faker.name().fullName()
        );
    }
    
    public static User randomWithShortPassword() {
        return new User(
            faker.internet().emailAddress(),
            faker.number().digits(5), 
            faker.name().fullName()
        );
    }
}
