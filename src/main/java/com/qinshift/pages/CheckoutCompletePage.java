package com.qinshift.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckoutCompletePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ProductPage.class);

    private static final By FINISH_BUTTON = By.id("back-to-products");
    private static final By ORDER_COMPLETED_TEXT = By.xpath("//h2[@class='complete-header' and text()='Thank you for your order!']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getOrderCompleted() {
        logger.info("Order completed text displayed");
        String getOrderCompleted = driver.findElement(ORDER_COMPLETED_TEXT).getText();
        logger.debug("Order completed text contains: {}", getOrderCompleted);
        return getOrderCompleted;
    }

    public WebElement getFinishButton() {
        logger.info("Clock on Checkout button");
        WebElement getContinueButton = driver.findElement(FINISH_BUTTON);
        return getContinueButton;
    }
}
