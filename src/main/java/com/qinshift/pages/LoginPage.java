package com.qinshift.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    private static final By USERNAME_INPUT = By.id("user-name");
    private static final By PASSWORD_INPUT = By.name("password");
    private static final By LOGIN_BUTTON = By.className("submit-button");
    private static final By ERROR_MESSAGE_LABEL = By.tagName("h3");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void doLogIn(String username, String password, boolean pressEnterKey) {
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        if (pressEnterKey) {
            driver.findElement(LOGIN_BUTTON).sendKeys(Keys.ENTER);
        } else {
            driver.findElement(LOGIN_BUTTON).click();
        }
    }

    public WebElement getErrorMessageElement() {
        return driver.findElement(ERROR_MESSAGE_LABEL);
    }

    public String getErrorMessageText() {
        return getErrorMessageElement().getText();
    }
}
