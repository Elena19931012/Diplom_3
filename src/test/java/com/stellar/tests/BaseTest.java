package com.stellar.tests;

import com.stellar.utils.WebDriverFactory;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

@RunWith(Parameterized.class)
public abstract class BaseTest {
    
    protected WebDriver driver;
    protected final String browser;
    protected static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    
    @Parameterized.Parameters(name = "Браузер: {0}")
    public static Object[][] getBrowsers() {
        return new Object[][]{
            {"chrome"},
            {"yandex"}
        };
    }
    
    public BaseTest(String browser) {
        this.browser = browser;
    }
    
    @Before
    @Step("Инициализация браузера {browser}")
    public void setUp() {
        driver = WebDriverFactory.createDriver(browser);
        driver.get(BASE_URL);
    }
    
    @After
    @Step("Закрытие браузера")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
