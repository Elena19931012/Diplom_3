package com.stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Локаторы
    @FindBy(xpath = ".//button[text()='Войти в аккаунт']")
    private WebElement loginButton;
    
    @FindBy(xpath = ".//p[text()='Личный Кабинет']/parent::a")
    private WebElement personalCabinetButton;
    
    @FindBy(xpath = ".//button[text()='Оформить заказ']")
    private WebElement orderButton;
    
    @FindBy(xpath = ".//div[@class='AppHeader_header__logo__2D0X2']")
    private WebElement logoButton;
    
    @FindBy(xpath = "//div[contains(@class, 'tab_tab__1SPyG')][.//span[text()='Булки']]")
    private WebElement bunsTab;
    
    @FindBy(xpath = "//div[contains(@class, 'tab_tab__1SPyG')][.//span[text()='Соусы']]")
    private WebElement saucesTab;
    
    @FindBy(xpath = "//div[contains(@class, 'tab_tab__1SPyG')][.//span[text()='Начинки']]")
    private WebElement fillingsTab;
    
    @FindBy(xpath = "//h2[text()='Булки']")
    private WebElement bunsSection;
    
    @FindBy(xpath = "//h2[text()='Соусы']")
    private WebElement saucesSection;
    
    @FindBy(xpath = "//h2[text()='Начинки']")
    private WebElement fillingsSection;
    
    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    @Step("Нажатие на кнопку 'Войти в аккаунт'")
    public LoginPage clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return new LoginPage(driver);
    }
    
    @Step("Нажатие на кнопку 'Личный Кабинет'")
    public void clickPersonalCabinetButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalCabinetButton)).click();
    }
    
    @Step("Нажатие на вкладку 'Булки'")
    public void clickBunsTab() {
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(bunsTab));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tab);
        
        try {
            tab.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tab);
        }
        
        wait.until(ExpectedConditions.visibilityOf(bunsSection));
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    @Step("Нажатие на вкладку 'Соусы'")
    public void clickSaucesTab() {
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(saucesTab));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tab);
        
        try {
            tab.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tab);
        }
        
        wait.until(ExpectedConditions.visibilityOf(saucesSection));
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    @Step("Нажатие на вкладку 'Начинки'")
    public void clickFillingsTab() {
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(fillingsTab));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tab);
        
        try {
            tab.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tab);
        }
        
        wait.until(ExpectedConditions.visibilityOf(fillingsSection));
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    @Step("Проверка видимости раздела 'Булки'")
    public boolean isBunsSectionVisible() {
        return wait.until(ExpectedConditions.visibilityOf(bunsSection)).isDisplayed();
    }
    
    @Step("Проверка видимости раздела 'Соусы'")
    public boolean isSaucesSectionVisible() {
        return wait.until(ExpectedConditions.visibilityOf(saucesSection)).isDisplayed();
    }
    
    @Step("Проверка видимости раздела 'Начинки'")
    public boolean isFillingsSectionVisible() {
        return wait.until(ExpectedConditions.visibilityOf(fillingsSection)).isDisplayed();
    }
    
    @Step("Нажатие на логотип")
    public void clickLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(logoButton)).click();
    }
    
    @Step("Проверка отображения кнопки 'Оформить заказ' (пользователь авторизован)")
    public boolean isOrderButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(orderButton)).isDisplayed();
    }
    
    @Step("Проверка что вкладка 'Булки' активна")
    public boolean isBunsTabActive() {
        WebElement tab = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//div[contains(@class, 'tab_tab__1SPyG')][.//span[text()='Булки']]")));
        return tab.getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }
    
    @Step("Проверка что вкладка 'Соусы' активна")
    public boolean isSaucesTabActive() {
        WebElement tab = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//div[contains(@class, 'tab_tab__1SPyG')][.//span[text()='Соусы']]")));
        return tab.getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }
    
    @Step("Проверка что вкладка 'Начинки' активна")
    public boolean isFillingsTabActive() {
        WebElement tab = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//div[contains(@class, 'tab_tab__1SPyG')][.//span[text()='Начинки']]")));
        return tab.getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }
}
