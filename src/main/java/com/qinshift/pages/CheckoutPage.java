package com.qinshift.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckoutPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ProductPage.class);

    private static final By FIRST_NAME_INPUT = By.xpath("//input[@placeholder='First Name']");
    private static final By LAST_NAME_INPUT = By.xpath("//input[@placeholder='Last Name']");
    private static final By POSTAL_CODE_BUTTON = By.xpath("//input[@placeholder='Zip/Postal Code']");

    private static final By CONTINUE_BUTTON = By.id("continue");
    private static final By CANCEL_BUTTON = By.xpath("//button[@class='btn btn_secondary back btn_medium cart_cancel_link']");


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void setUserData(String firstName, String lastName, String postalCode) {
        logger.info("Setting firstNamee, lastName, postalcode", firstName, lastName, postalCode);
        driver.findElement(FIRST_NAME_INPUT).sendKeys(firstName);
        driver.findElement(LAST_NAME_INPUT).sendKeys(lastName);
        driver.findElement(POSTAL_CODE_BUTTON).sendKeys(postalCode);
    }

    public WebElement getCancelButton() {
        logger.info("Remove Product from Shopping Cart");
        WebElement getCancelButton = driver.findElement(CANCEL_BUTTON);
        return getCancelButton;
    }

    public WebElement getContinueButton() {
        logger.info("Clock on Checkout button");
        WebElement getContinueButton = driver.findElement(CONTINUE_BUTTON);
        return getContinueButton;
    }
}
