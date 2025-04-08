package com.qinshift.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckoutOverviewPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ProductPage.class);

    private static final By FINISH_BUTTON = By.id("finish");
    private static final By CANCEL_BUTTON = By.xpath("//button[@class='btn btn_secondary back btn_medium cart_cancel_link']");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getCancelButton() {
        logger.info("Remove Product from Shopping Cart");
        WebElement getCancelButton = driver.findElement(CANCEL_BUTTON);
        return getCancelButton;
    }

    public WebElement getFinishButton() {
        logger.info("Clock on Checkout button");
        WebElement getContinueButton = driver.findElement(FINISH_BUTTON);
        return getContinueButton;
    }
}
