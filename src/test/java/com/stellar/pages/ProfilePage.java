package com.stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Локаторы
    @FindBy(xpath = ".//button[text()='Выход']")
    private WebElement logoutButton;
    
    @FindBy(xpath = ".//a[text()='Профиль']")
    private WebElement profileLink;
    
    @FindBy(xpath = ".//p[text()='Конструктор']/parent::a")
    private WebElement constructorButton;
    
    @FindBy(xpath = ".//div[@class='AppHeader_header__logo__2D0X2']")
    private WebElement logoButton;
    
    @FindBy(xpath = ".//input[@name='name']")
    private WebElement nameInput;
    
    @FindBy(xpath = ".//input[@value]")
    private WebElement emailInput;
    
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    @Step("Нажатие кнопки выхода")
    public MainPage clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
        return new MainPage(driver);
    }
    
    @Step("Нажатие кнопки конструктора")
    public MainPage clickConstructorButton() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
        return new MainPage(driver);
    }
    
    @Step("Нажатие на логотип")
    public MainPage clickLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(logoButton)).click();
        return new MainPage(driver);
    }
    
    @Step("Проверка загрузки страницы профиля")
    public boolean isProfilePageLoaded() {
        return wait.until(ExpectedConditions.visibilityOf(nameInput)).isDisplayed() &&
               wait.until(ExpectedConditions.visibilityOf(emailInput)).isDisplayed();
    }
}
