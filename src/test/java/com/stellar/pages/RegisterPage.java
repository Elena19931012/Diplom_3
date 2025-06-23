package com.stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(xpath = "//input[@placeholder='Имя' or @name='name' and not(@type='email')]")
    private WebElement nameInput;
    
    @FindBy(xpath = "//input[@name='name' and @type='email'] | //input[@placeholder='E-mail']")
    private WebElement emailInput;
    
    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;
    
    @FindBy(xpath = ".//button[text()='Зарегистрироваться']")
    private WebElement registerButton;
    
    @FindBy(xpath = ".//a[text()='Войти']")
    private WebElement loginLink;
    
    @FindBy(xpath = "//p[contains(@class, 'input__error') and text()='Некорректный пароль']")
    private WebElement passwordError;
    
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    @Step("Ввод имени: {name}")
    public RegisterPage enterName(String name) {
        wait.until(ExpectedConditions.visibilityOf(nameInput)).sendKeys(name);
        return this;
    }
    
    @Step("Ввод email: {email}")
    public RegisterPage enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(email);
        return this;
    }
    
    @Step("Ввод пароля: {password}")
    public RegisterPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
        return this;
    }
    
    @Step("Нажатие кнопки регистрации")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }
    
    @Step("Нажатие ссылки входа")
    public LoginPage clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        return new LoginPage(driver);
    }
    
    @Step("Проверка отображения ошибки пароля")
    public boolean isPasswordErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(passwordError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    @Step("Регистрация нового пользователя с именем: {name}, email: {email}, паролем: {password}")
    public Object register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
        
        try {
            wait.until(ExpectedConditions.visibilityOf(passwordError));
            return this;
        } catch (Exception e) {
            try {
                wait.until(ExpectedConditions.invisibilityOf(registerButton));
                return new LoginPage(driver); 
            } catch (Exception ex) {
                return this; 
            }
        }
    }
    
    @Step("Проверка нахождения на странице регистрации")
    public boolean isStillOnRegisterPage() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(registerButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
