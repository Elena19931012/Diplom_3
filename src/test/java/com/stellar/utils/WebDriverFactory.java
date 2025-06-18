package com.stellar.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import java.time.Duration;

public class WebDriverFactory {
    
    private static final String YANDEX_BROWSER_PATH = "C:\\Program Files\\Yandex\\YandexBrowser\\browser.exe";
    
    public static WebDriver createDriver(String browser) {
        WebDriver driver;
        
        switch (browser.toLowerCase()) {
            case "yandex":
                WebDriverManager.chromedriver().setup();
                ChromeOptions yandexOptions = new ChromeOptions();
                
                File yandexBrowser = new File(YANDEX_BROWSER_PATH);
                if (yandexBrowser.exists()) {
                    yandexOptions.setBinary(YANDEX_BROWSER_PATH);
                    yandexOptions.addArguments("--remote-allow-origins=*");
                    driver = new ChromeDriver(yandexOptions);
                } else {
                    System.out.println("Яндекс Браузер не найден. Вместо этого использую Chrome.");
                    driver = new ChromeDriver();
                }
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
                break;
        }
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }
}
