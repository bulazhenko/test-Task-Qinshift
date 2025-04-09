package com.qinshift.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    private static final By USERNAME_INPUT = By.id("user-name");
    private static final By PASSWORD_INPUT = By.name("password");
    private static final By LOGIN_BUTTON = By.className("submit-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void doLogInEnterKey(String username, String password, boolean pressEnterKey) {
        logger.info("Logging in with username: {}, password (masked): {}, pressEnterKey: {}", username, maskPassword(password), pressEnterKey);
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        if (pressEnterKey) {
            driver.findElement(LOGIN_BUTTON).sendKeys(Keys.ENTER);
        } else {
            driver.findElement(LOGIN_BUTTON).click();
        }
        logger.info("Login action performed");
    }

    public void doLogIn(String username, String password) {
        logger.info("Logging in with username: {}, password (masked): {}", username, maskPassword(password));
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        logger.info("Login action performed");
    }

    private String maskPassword(String password) {
        if (password == null || password.isEmpty()) {
            return "";
        }
        return "*".repeat(password.length());
    }
}
