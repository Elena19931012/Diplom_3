package com.stellar.tests;

import com.stellar.api.UserClient;
import com.stellar.models.User;
import com.stellar.pages.MainPage;
import com.stellar.pages.ProfilePage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {
    
    private User testUser;
    private UserClient userClient = new UserClient();
    
    public LoginTest(String browser) {
        super(browser);
    }
    
    @Before
    public void createTestUser() {
        testUser = User.random();
        Response response = userClient.registerUser(testUser.getEmail(), testUser.getPassword(), testUser.getName());
        testUser.setAccessToken(response.path("accessToken").toString());
    }
    
    @After
    public void cleanUp() {
        if (testUser != null && testUser.getAccessToken() != null) {
            userClient.deleteUser(testUser.getAccessToken());
        }
    }
    
    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной странице")
    @Description("Тест проверяет возможность входа через кнопку 'Войти в аккаунт' на главной странице")
    public void loginViaLoginToAccountButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton()
                .login(testUser.getEmail(), testUser.getPassword());
        
        assertTrue("Пользователь должен войти в систему и видеть кнопку 'Оформить заказ'", mainPage.isOrderButtonDisplayed());
    }
    
    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Тест проверяет возможность входа через кнопку 'Личный кабинет'")
    public void loginViaPersonalCabinetButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalCabinetButton();
        
        new com.stellar.pages.LoginPage(driver)
                .login(testUser.getEmail(), testUser.getPassword());
        
        assertTrue("Пользователь должен войти в систему и видеть кнопку 'Оформить заказ'", mainPage.isOrderButtonDisplayed());
    }
    
    @Test
    @DisplayName("Вход через ссылку в форме регистрации")
    @Description("Тест проверяет возможность входа через ссылку на странице регистрации")
    public void loginViaRegistrationFormLinkTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton()
                .clickRegisterLink()
                .clickLoginLink()
                .login(testUser.getEmail(), testUser.getPassword());
        
        assertTrue("Пользователь должен войти в систему и видеть кнопку 'Оформить заказ'", mainPage.isOrderButtonDisplayed());
    }
    
    @Test
    @DisplayName("Вход через ссылку в форме восстановления пароля")
    @Description("Тест проверяет возможность входа через ссылку на странице восстановления пароля")
    public void loginViaPasswordRecoveryFormLinkTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton()
                .clickRecoverPasswordLink()
                .clickLoginLink()
                .login(testUser.getEmail(), testUser.getPassword());
        
        assertTrue("Пользователь должен войти в систему и видеть кнопку 'Оформить заказ'", mainPage.isOrderButtonDisplayed());
    }
}
