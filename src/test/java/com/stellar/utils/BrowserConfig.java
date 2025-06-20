package com.stellar.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BrowserConfig {
    
    private static final String DEFAULT_BROWSER = "chrome";
    private static final String PROPERTIES_FILE_PATH = "src/test/resources/config.properties";
    private static final String BROWSER_ENV_VARIABLE = "BROWSER";
    private static final String BROWSER_SYSTEM_PROPERTY = "browser";
    
    public static String getBrowser() {
        String browser = System.getenv(BROWSER_ENV_VARIABLE);
        
        if (browser == null || browser.isEmpty()) {
            browser = System.getProperty(BROWSER_SYSTEM_PROPERTY);
        }
        
        if (browser == null || browser.isEmpty()) {
            browser = getPropertyFromFile("browser");
        }
        
        return (browser != null && !browser.isEmpty()) ? browser : DEFAULT_BROWSER;
    }
    
    private static String getPropertyFromFile(String propertyName) {
        Properties properties = new Properties();
        
        try (InputStream input = new FileInputStream(PROPERTIES_FILE_PATH)) {
            properties.load(input);
            return properties.getProperty(propertyName);
        } catch (IOException e) {
            System.out.println("Could not read properties file. Using default settings.");
            return null;
        }
    }
}
