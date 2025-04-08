package com.qinshift.webDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SingletonWebDriver {
    private static WebDriver driver;

    private SingletonWebDriver() { }

    public static WebDriver getDriver() {
        if (driver == null) {
            synchronized (SingletonWebDriver.class) {  // Simple Thread-safe singleton
                if (driver == null) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("start-maximized");
                    options.addArguments("--guest");
                    options.addArguments("--disable-features=PasswordBreachDetection");
                    options.addArguments("--disable-features=AutofillServerCommunication");
                    options.addArguments("--disable-save-password-bubble");
                    options.addArguments("--disable-password-generation");
                    System.setProperty("webdriver.http.factory", "jdk-http-client");

                    driver = new ChromeDriver(options);
                }
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
