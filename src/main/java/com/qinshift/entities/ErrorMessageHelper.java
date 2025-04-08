package com.qinshift.entities;

import com.qinshift.pages.BasePage;
import com.qinshift.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorMessageHelper extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    public ErrorMessageHelper(WebDriver driver) {
        super(driver);
    }

    private static final By ERROR_MESSAGE_LABEL = By.tagName("h3");

    public WebElement getErrorMessageElement() {
        logger.info("Getting error message element");
        return driver.findElement(ERROR_MESSAGE_LABEL);
    }

    public String getErrorMessageText() {
        WebElement errorMessageElement = getErrorMessageElement();
        String errorMessage = errorMessageElement.getText();
        logger.info("Error message text: {}", errorMessage);
        return errorMessage;
    }
}
