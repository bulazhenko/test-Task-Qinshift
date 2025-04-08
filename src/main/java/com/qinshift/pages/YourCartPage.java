package com.qinshift.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YourCartPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ProductPage.class);

    private static final By CHECKOUT_BUTTON = By.xpath("//button[@class='btn btn_action btn_medium checkout_button ']");
    private static final By REMOVE_BUTTON = By.xpath("//button[@class='btn btn_secondary btn_small cart_button']");

    public YourCartPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getRemoveButton() {
        logger.info("Remove Product from Shopping Cart");
        WebElement getRemoveButton = driver.findElement(REMOVE_BUTTON);
        return getRemoveButton;
    }

    public WebElement getCheckoutButton() {
        logger.info("Clock on Checkout button");
        WebElement getCheckoutButton = driver.findElement(CHECKOUT_BUTTON);
        return getCheckoutButton;
    }
}
