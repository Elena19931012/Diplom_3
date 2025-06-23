package com.stellar.tests;

import com.stellar.utils.BrowserConfig;
import com.stellar.utils.WebDriverFactory;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {
    
    protected WebDriver driver;
    protected static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    
    @Before
    @Step("Инициализация браузера")
    public void setUp() {
        String browser = BrowserConfig.getBrowser();
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
