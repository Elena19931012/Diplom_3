package com.stellar.tests;

import com.stellar.pages.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ConstructorTest extends BaseTest {
    
    @Test
    @DisplayName("Переход на вкладку булок")
    @Description("Тест проверяет возможность перехода на вкладку булок в конструкторе")
    public void navigateToBunsTabTest() {
        MainPage mainPage = new MainPage(driver);
        
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();
        assertTrue("После клика на булки, вкладка 'Булки' должна быть активна", mainPage.isBunsTabActive());
    }
    
    @Test
    @DisplayName("Переход на вкладку соусов")
    @Description("Тест проверяет возможность перехода на вкладку соусов в конструкторе")
    public void navigateToSaucesTabTest() {
        MainPage mainPage = new MainPage(driver);
        
        mainPage.clickSaucesTab();
        assertTrue("После клика вкладка 'Соусы' должна быть активна", mainPage.isSaucesTabActive());
    }
    
    @Test
    @DisplayName("Переход на вкладку начинок")
    @Description("Тест проверяет возможность перехода на вкладку начинок в конструкторе")
    public void navigateToFillingsTabTest() {
        MainPage mainPage = new MainPage(driver);
        
        mainPage.clickFillingsTab();
        assertTrue("После клика вкладка 'Начинки' должна быть активна", mainPage.isFillingsTabActive());
    }
}
