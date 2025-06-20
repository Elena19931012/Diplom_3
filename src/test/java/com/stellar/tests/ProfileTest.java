package com.stellar.tests;

import com.stellar.api.UserClient;
import com.stellar.models.User;
import com.stellar.pages.MainPage;
import com.stellar.pages.ProfilePage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ProfileTest extends BaseTest {
    
    private User testUser;
    private UserClient userClient = new UserClient();
    
    @Before
    public void createTestUserAndLogin() {
        testUser = User.random();
        Response response = userClient.registerUser(testUser.getEmail(), testUser.getPassword(), testUser.getName());
        testUser.setAccessToken(response.path("accessToken").toString());
        
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton()
                .login(testUser.getEmail(), testUser.getPassword());
    }
    
    @After
    public void cleanUp() {
        if (testUser != null && testUser.getAccessToken() != null) {
            userClient.deleteUser(testUser.getAccessToken());
        }
    }
    
    @Test
    @DisplayName("Переход в личный кабинет")
    @Description("Тест проверяет возможность перехода в личный кабинет")
    public void navigateToPersonalCabinetTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalCabinetButton();
        
        ProfilePage profilePage = new ProfilePage(driver);
        assertTrue("Страница профиля должна загрузиться", profilePage.isProfilePageLoaded());
    }
    
    @Test
    @DisplayName("Переход из профиля в конструктор через кнопку")
    @Description("Тест проверяет возможность перехода из профиля в конструктор через кнопку 'Конструктор'")
    public void navigateFromProfileToConstructorViaButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalCabinetButton();
        
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickConstructorButton();
        
        assertTrue("На главной странице должна отображаться кнопка заказа после перехода", mainPage.isOrderButtonDisplayed());
    }
    
    @Test
    @DisplayName("Переход из профиля в конструктор через логотип")
    @Description("Тест проверяет возможность перехода из профиля в конструктор через логотип")
    public void navigateFromProfileToConstructorViaLogoTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalCabinetButton();
        
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLogo();
        
        assertTrue("На главной странице должна отображаться кнопка заказа после перехода", mainPage.isOrderButtonDisplayed());
    }
    
    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Тест проверяет возможность выхода из аккаунта")
    public void logoutTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalCabinetButton();
        
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLogoutButton();
        
        mainPage.clickPersonalCabinetButton();
        assertTrue("После выхода нажатие на личный кабинет должно перенаправить на страницу входа", 
                   driver.getCurrentUrl().contains("login"));
    }
}
