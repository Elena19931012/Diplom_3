package com.stellar.tests;

import com.stellar.pages.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {
    
    public ConstructorTest(String browser) {
        super(browser);
    }
    
    @Test
    @DisplayName("Переход на вкладку булок")
    @Description("Тест проверяет возможность перехода на вкладку булок в конструкторе")
    public void navigateToBunsTabTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickBunsTab();
        
        assertTrue("Раздел булок должен быть видимым", mainPage.isBunsSectionVisible());
    }
    
    @Test
    @DisplayName("Переход на вкладку соусов")
    @Description("Тест проверяет возможность перехода на вкладку соусов в конструкторе")
    public void navigateToSaucesTabTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesTab();
        
        assertTrue("Раздел соусов должен быть видимым", mainPage.isSaucesSectionVisible());
    }
    
    @Test
    @DisplayName("Переход на вкладку начинок")
    @Description("Тест проверяет возможность перехода на вкладку начинок в конструкторе")
    public void navigateToFillingsTabTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsTab();
        
        assertTrue("Раздел начинок должен быть видимым", mainPage.isFillingsSectionVisible());
    }
}
