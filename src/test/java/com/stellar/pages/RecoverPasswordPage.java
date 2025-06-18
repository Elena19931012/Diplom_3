package com.stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecoverPasswordPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    @FindBy(xpath = ".//input[@name='email']")
    private WebElement emailInput;
    
    @FindBy(xpath = ".//button[text()='Восстановить']")
    private WebElement recoverButton;
    
    @FindBy(xpath = ".//a[text()='Войти']")
    private WebElement loginLink;
    
    public RecoverPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    @Step("Ввод емайла: {email}")
    public RecoverPasswordPage enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(email);
        return this;
    }
    
    @Step("Наждатие кнопки")
    public void clickRecoverButton() {
        wait.until(ExpectedConditions.elementToBeClickable(recoverButton)).click();
    }
    
    @Step("Нажатие кнопки войти")
    public LoginPage clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        return new LoginPage(driver);
    }
}
