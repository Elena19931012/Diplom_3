package com.stellar.tests;

import com.stellar.api.UserClient;
import com.stellar.models.User;
import com.stellar.pages.LoginPage;
import com.stellar.pages.MainPage;
import com.stellar.pages.RegisterPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class RegistrationTest extends BaseTest {
    
    private User testUser;
    private UserClient userClient = new UserClient();
    
    @After
    public void cleanUp() {
        if (testUser != null) {
            loginUserIfNeeded();
            if (testUser.getAccessToken() != null) {
                userClient.deleteUser(testUser.getAccessToken());
            }
        }
    }
    
    @Step("Логин, если токен доступа не установлен")
    private void loginUserIfNeeded() {
        if (testUser.getAccessToken() == null) {
            Response response = userClient.loginUser(testUser.getEmail(), testUser.getPassword());
            if (response.statusCode() == 200) {
                testUser.setAccessToken(response.path("accessToken").toString());
            }
        }
    }
    
    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Тест проверяет возможность успешной регистрации пользователя с валидными данными")
    public void successfulRegistrationTest() {
        testUser = User.random();
        
        MainPage mainPage = new MainPage(driver);
        Object result = mainPage.clickLoginButton()
                .clickRegisterLink()
                .register(testUser.getName(), testUser.getEmail(), testUser.getPassword());
        
        assertTrue("Пользователь должен быть перенаправлен на страницу входа после успешной регистрации", 
                result instanceof LoginPage);
    }
    
    @Test
    @DisplayName("Регистрация невозможна с коротким паролем")
    @Description("Тест проверяет, что пользователь не может зарегистрироваться с паролем менее 6 символов")
    public void registrationFailsWithShortPasswordTest() {
        testUser = User.randomWithShortPassword();
        
        MainPage mainPage = new MainPage(driver);
        RegisterPage registerPage = mainPage.clickLoginButton()
                .clickRegisterLink();
                
        registerPage.enterName(testUser.getName())
                   .enterEmail(testUser.getEmail())
                   .enterPassword(testUser.getPassword());
        
        registerPage.clickRegisterButton();
        
        assertTrue("Сообщение об ошибке пароля должно отображаться", registerPage.isPasswordErrorDisplayed());
    }
}
