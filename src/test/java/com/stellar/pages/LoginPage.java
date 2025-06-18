package com.stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Локаторы
    @FindBy(xpath = "//fieldset[1]//input[@name='name']")
    private WebElement emailInput;
    
    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;
    
    @FindBy(xpath = ".//button[text()='Войти']")
    private WebElement submitButton;
    
    @FindBy(xpath = ".//a[text()='Зарегистрироваться']")
    private WebElement registerLink;
    
    @FindBy(xpath = ".//a[text()='Восстановить пароль']")
    private WebElement recoverPasswordLink;
    
    @FindBy(xpath = ".//p[text()='Некорректный пароль']")
    private WebElement incorrectPasswordError;
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    @Step("Ввод email: {email}")
    public LoginPage enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(email);
        return this;
    }
    
    @Step("Ввод пароля: {password}")
    public LoginPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
        return this;
    }
    
    @Step("Нажатие кнопки входа")
    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }
    
    @Step("Нажатие ссылки регистрации")
    public RegisterPage clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
        return new RegisterPage(driver);
    }
    
    @Step("Нажатие ссылки восстановления пароля")
    public RecoverPasswordPage clickRecoverPasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(recoverPasswordLink)).click();
        return new RecoverPasswordPage(driver);
    }
    
    @Step("Проверка отображения ошибки о неверном пароле")
    public boolean isIncorrectPasswordErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(incorrectPasswordError)).isDisplayed();
    }
    
    @Step("Вход с email: {email} и паролем: {password}")
    public MainPage login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSubmitButton();
        return new MainPage(driver);
    }
}
